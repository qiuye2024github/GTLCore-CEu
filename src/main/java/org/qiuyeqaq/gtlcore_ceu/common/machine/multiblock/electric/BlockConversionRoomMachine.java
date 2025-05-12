package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;
import org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part.BlockBusPartMachine;
import org.qiuyeqaq.gtlcore_ceu.utils.Registries;

import java.util.*;

public class BlockConversionRoomMachine extends StorageMachine {

    private static final List<int[]> poses1 = new ArrayList<>();
    private static final List<int[]> poses2 = new ArrayList<>();
    private static final Map<Block, Block> covRecipe = new HashMap<>();

    static {
        for (int i = -2; i <= 2; i++) {
            for (int j = -1; j >= -5; j--) {
                for (int k = -2; k <= 2; k++) {
                    poses1.add(new int[] { i, j, k });
                }
            }
        }
        for (int i = -4; i <= 4; i++) {
            for (int j = -1; j >= -7; j--) {
                for (int k = -4; k <= 4; k++) {
                    poses2.add(new int[] { i, j, k });
                }
            }
        }
        covRecipe.put(Blocks.BONE_BLOCK, Registries.getBlock("kubejs:essence_block"));
        covRecipe.put(Blocks.OAK_LOG, Blocks.CRIMSON_STEM);
        covRecipe.put(Blocks.BIRCH_LOG, Blocks.WARPED_STEM);
        covRecipe.put(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Calcium), Blocks.BONE_BLOCK);
        covRecipe.put(Blocks.MOSS_BLOCK, Blocks.SCULK);
        covRecipe.put(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK);
        covRecipe.put(Registries.getBlock("kubejs:infused_obsidian"), Registries.getBlock("kubejs:draconium_block_charged"));
    }

    private final int am;
    private final List<int[]> poses;

    private Set<BlockBusPartMachine> blockBusPartMachine = new HashSet<>();

    public BlockConversionRoomMachine(IMachineBlockEntity holder, boolean isLarge) {
        super(holder, 1);
        this.am = isLarge ? 64 : 4;
        this.poses = isLarge ? poses2 : poses1;
    }

    @Override
    protected boolean filter(@NotNull ItemStack itemStack) {
        return itemStack.getItem() == GTLCEuItems.CONVERSION_SIMULATE_CARD.get() ||
                itemStack.getItem() == GTLCEuItems.FAST_CONVERSION_SIMULATE_CARD.get();
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if (part instanceof BlockBusPartMachine busPartMachine) {
                blockBusPartMachine.add(busPartMachine);
            }
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        blockBusPartMachine = null;
    }

    @Override
    public boolean onWorking() {
        boolean value = super.onWorking();
        if (getOffsetTimer() % 20 == 0) {
            int amount = getTier() * am - 7;
            if (blockBusPartMachine != null && !getMachineStorageItem().isEmpty()) {
                int a = amount;
                if (getMachineStorageItem().is(GTLCEuItems.FAST_CONVERSION_SIMULATE_CARD.get())) {
                    a = 81 * 64 * 3;
                }
                for (BlockBusPartMachine busPartMachine : blockBusPartMachine) {
                    CustomItemStackHandler stackTransfer = busPartMachine.getInventory().storage;

                    for (int i = 0; a > 0 && i < stackTransfer.getSlots(); i++) {
                        ItemStack itemStack = stackTransfer.getStackInSlot(i);
                        if (itemStack.getItem() instanceof BlockItem blockItem &&
                                covRecipe.containsKey(blockItem.getBlock())) {
                            int count = itemStack.getCount();
                            a -= count;
                            stackTransfer.setStackInSlot(i, new ItemStack(covRecipe.get(blockItem.getBlock()).asItem(), count));
                        }
                    }
                }
            } else {
                Level level = getLevel();
                if (level != null) {
                    int[] pos = new int[] {};
                    for (int i = 0; i < amount; i++) {
                        int[] pos_0 = poses.get((int) (Math.random() * poses.size()));
                        if (pos_0 != pos) {
                            pos = pos_0;
                            BlockPos blockPos = getPos().offset(pos[0], pos[1], pos[2]);
                            Block block = level.getBlockState(blockPos).getBlock();
                            if (covRecipe.containsKey(block)) {
                                level.setBlockAndUpdate(blockPos, covRecipe.get(block).defaultBlockState());
                            }
                        } else {
                            i--;
                        }
                    }
                }
            }
        }
        return value;
    }

    public int getConversionCount() {
        if (!getMachineStorageItem().isEmpty() && getMachineStorageItem().is(GTLCEuItems.FAST_CONVERSION_SIMULATE_CARD.get())) {
            return 81 * am;
        }
        return getTier() * am - 7;
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.translatable("gtceu.machine.block_conversion_room.am", getConversionCount()));
    }

    public static Collection<Block> getConversionBlock() {
        return Collections.unmodifiableCollection(covRecipe.values());
    }
}
