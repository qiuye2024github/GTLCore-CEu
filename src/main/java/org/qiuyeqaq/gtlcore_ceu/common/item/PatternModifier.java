package org.qiuyeqaq.gtlcore_ceu.common.item;

import org.qiuyeqaq.gtlcore_ceu.api.item.tool.ae2.patternTool.Ae2BaseProcessingPattern;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.component.IItemUIFactory;
import com.gregtechceu.gtceu.integration.ae2.gui.widget.AETextInputButtonWidget;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import appeng.api.inventories.InternalInventory;
import appeng.api.parts.IPart;
import appeng.blockentity.crafting.PatternProviderBlockEntity;
import appeng.blockentity.networking.CableBusBlockEntity;
import appeng.parts.crafting.PatternProviderPart;
import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import lombok.Setter;

import java.util.HashMap;

@Setter
public class PatternModifier implements IItemUIFactory {

    public static final PatternModifier INSTANCE = new PatternModifier();
    private int Ae2PatternGeneratorScale = 1;
    private long Ae2PatternGeneratorMaxItemStack = 1000000L;
    private long Ae2PatternGeneratorMaxFluidStack = 1000000L;
    private int Ae2PatternGeneratorDivScale = 1;
    private int Ae2PatternGeneratorAppliedNumber = 1;

    @Override
    public ModularUI createUI(HeldItemUIFactory.HeldItemHolder heldItemHolder, Player player) {
        return new ModularUI(206, 124, heldItemHolder, player).widget(
                new WidgetGroup(0, 0, 206, 124)
                        .addWidget(new ImageWidget(8, 8, 190, 108, GuiTextures.DISPLAY))
                        .addWidget(new LabelWidget(12, 12, "AE样板倍乘器"))
                        .addWidget(new LabelWidget(12, 22, "设置倍数后，shift右键样板供应器方块使用"))
                        .addWidget(new LabelWidget(12, 32, "先做乘法，后做除法，数量限制对成品不生效"))
                        .addWidget(new LabelWidget(12, 42, "若应用次数为3，则代表执行三次应用次数为1的操作"))
                        .addWidget(new AETextInputButtonWidget(120, 46 + 4, 72, 12)
                                .setText(String.valueOf(Ae2PatternGeneratorScale))
                                .setOnConfirm(this::setAe2PatternGeneratorScale)
                                .setButtonTooltips(Component.literal("设置模板乘数")))
                        .addWidget(new AETextInputButtonWidget(120, 60 + 4, 72, 12)
                                .setText(String.valueOf(Ae2PatternGeneratorDivScale))
                                .setOnConfirm(this::setAe2PatternGeneratorDivScale)
                                .setButtonTooltips(Component.literal("设置模板除数")))
                        .addWidget(new AETextInputButtonWidget(120, 74 + 4, 72, 12)
                                .setText(String.valueOf(Ae2PatternGeneratorMaxItemStack))
                                .setOnConfirm(this::setAe2PatternGeneratorMaxItemStack)
                                .setButtonTooltips(Component.literal("设置乘法后最大物品/个")))
                        .addWidget(new AETextInputButtonWidget(120, 88 + 4, 72, 12)
                                .setText(String.valueOf(Ae2PatternGeneratorMaxFluidStack))
                                .setOnConfirm(this::setAe2PatternGeneratorMaxFluidStack)
                                .setButtonTooltips(Component.literal("设置乘法后最大流体/桶")))
                        .addWidget(new AETextInputButtonWidget(120, 102 + 4, 72, 12)
                                .setText(String.valueOf(Ae2PatternGeneratorAppliedNumber))
                                .setOnConfirm(this::setAe2PatternGeneratorAppliedNumber)
                                .setButtonTooltips(Component.literal("一次使用的应用次数(<=16)"))))
                .background(GuiTextures.BACKGROUND);
    }

    private void setAe2PatternGeneratorAppliedNumber(String s) {
        Ae2PatternGeneratorAppliedNumber = Math.min(16, Integer.parseInt(s));
    }

    private void setAe2PatternGeneratorMaxFluidStack(String s) {
        Ae2PatternGeneratorMaxFluidStack = Integer.parseInt(s);
    }

    private void setAe2PatternGeneratorMaxItemStack(String s) {
        Ae2PatternGeneratorMaxItemStack = Integer.parseInt(s);
    }

    private void setAe2PatternGeneratorDivScale(String s) {
        Ae2PatternGeneratorDivScale = Math.min(Integer.MAX_VALUE, Integer.parseInt(s));
    }

    private void setAe2PatternGeneratorScale(String s) {
        Ae2PatternGeneratorScale = Math.min(Integer.MAX_VALUE, Integer.parseInt(s));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Item item, Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player instanceof ServerPlayer serverPlayer) {
            HeldItemUIFactory.INSTANCE.openUI(serverPlayer, usedHand);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() instanceof ServerPlayer serverPlayer) {
            /*
             * shift右键乘法逻辑
             */
            if (serverPlayer.isShiftKeyDown()) {
                BlockPos pos = context.getClickedPos();
                Level level = context.getLevel();
                BlockEntity tile = level.getBlockEntity(pos);
                InternalInventory internalInventory;
                if (tile instanceof CableBusBlockEntity cable) {
                    // 获取点击方块坐标
                    Vec3 hitVec = context.getClickLocation();
                    // 计算具体点击位置
                    Vec3 hitInBlock = new Vec3(hitVec.x - (double) pos.getX(), hitVec.y - (double) pos.getY(), hitVec.z - (double) pos.getZ());
                    IPart part = cable.getCableBus().selectPartLocal(hitInBlock).part;
                    internalInventory = (part instanceof PatternProviderPart providerPart) ?
                            providerPart.getLogic().getPatternInv() : null;
                } else {
                    internalInventory = (tile instanceof PatternProviderBlockEntity providerBlock) ?
                            providerBlock.getLogic().getPatternInv() : null;
                }

                if (internalInventory == null) {
                    serverPlayer.displayClientMessage(Component.literal("只能对着样板供应器使用")
                            .withStyle(), true);
                    return InteractionResult.FAIL;
                }

                for (int i = 0; i < Ae2PatternGeneratorAppliedNumber; ++i) {
                    HashMap<Integer, ItemStack> newItemStackHashMap = new HashMap<>();
                    for (int slot = 0; slot < internalInventory.size(); slot++) {
                        ItemStack itemStack = internalInventory.getStackInSlot(slot);
                        if (!itemStack.isEmpty()) {
                            ItemStack patternItemStack = getNewPatternItemStack(serverPlayer, itemStack);
                            newItemStackHashMap.put(slot, patternItemStack);
                        }

                    }
                    newItemStackHashMap.forEach((slot, itemStack) -> {
                        internalInventory.extractItem(slot, 1, false);
                        internalInventory.insertItem(slot, itemStack, false);
                    });
                }
                serverPlayer.displayClientMessage(Component.literal("已更新内部的样板，应用了%s次".formatted(Ae2PatternGeneratorAppliedNumber)), true);
            }
            if (!serverPlayer.isShiftKeyDown()) {
                serverPlayer.displayClientMessage(Component.literal("右键空气打开GUI"), true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private ItemStack getNewPatternItemStack(ServerPlayer serverPlayer, ItemStack itemStack) {
        Ae2BaseProcessingPattern ae2BaseProcessingPattern = new Ae2BaseProcessingPattern(itemStack, serverPlayer);
        ae2BaseProcessingPattern.setScale(Ae2PatternGeneratorScale,
                false,
                Ae2PatternGeneratorMaxItemStack,
                Ae2PatternGeneratorMaxFluidStack);
        ae2BaseProcessingPattern.setScale(Ae2PatternGeneratorDivScale, true);
        return ae2BaseProcessingPattern.getPatternItemStack();
    }
}
