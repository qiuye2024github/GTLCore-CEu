package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part;

import appeng.api.config.Actionable;
import appeng.api.networking.IGrid;
import appeng.api.networking.storage.IStorageService;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.MEStorage;
import appeng.util.prioritylist.IPartitionList;
import com.glodblock.github.extendedae.common.me.taglist.TagExpParser;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfigurator;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;
import com.gregtechceu.gtceu.integration.ae2.machine.MEInputBusPartMachine;
import com.gregtechceu.gtceu.integration.ae2.slot.ExportOnlyAEItemList;
import com.gregtechceu.gtceu.integration.ae2.slot.ExportOnlyAEItemSlot;
import com.gregtechceu.gtceu.integration.ae2.slot.ExportOnlyAESlot;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Reference2BooleanMap;
import it.unimi.dsi.fastutil.objects.Reference2BooleanOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TagFilterMEStockBusPartMachine extends MEInputBusPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(TagFilterMEStockBusPartMachine.class,
            MEInputBusPartMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    protected String tagWhite = "";

    @Persisted
    protected String tagBlack = "";

    @Getter
    @Setter
    @Persisted
    private boolean isCountSort = false;

    public TagFilterMEStockBusPartMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    protected NotifiableItemStackHandler createInventory(Object... args) {
        this.aeItemHandler = new ExportOnlyAEStockingItemList(this, CONFIG_SIZE);
        return this.aeItemHandler;
    }

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        super.attachConfigurators(configuratorPanel);
        configuratorPanel.attachConfigurators(new IFancyConfiguratorButton.Toggle(
                new TextTexture("A-Z"),
                new TextTexture("数量▼"),
                this::isCountSort,
                (clickData, pressed) -> setCountSort(pressed))
                .setTooltipsSupplier(pressed -> List.of(Component.literal("自动拉取排序方式"))));
        configuratorPanel.attachConfigurators(new FilterIFancyConfigurator());
    }

    @Override
    public void autoIO() {
        super.autoIO();
        if (getOffsetTimer() % 50 == 0) {
            refreshList();
            syncME();
        }
    }

    @Override
    protected void syncME() {
        IGrid grid = this.getMainNode().getGrid();
        if (grid == null) {
            return;
        }
        MEStorage networkInv = grid.getStorageService().getInventory();
        for (ExportOnlyAEItemSlot slot : this.aeItemHandler.getInventory()) {
            var config = slot.getConfig();
            if (config != null) {
                // Try to fill the slot
                var key = config.what();
                // try max fill Integer.MAX_VALUE
                long extracted = networkInv.extract(key, Integer.MAX_VALUE, Actionable.SIMULATE, actionSource);
                if (extracted > 0) {
                    slot.setStock(new GenericStack(key, extracted));
                    continue;
                }
            }
            slot.setStock(null);
        }
    }

    private void refreshList() {
        IGrid grid = this.getMainNode().getGrid();
        if (grid == null) {
            aeItemHandler.clearInventory(0);
            return;
        }
        IStorageService storageService = grid.getStorageService();
        MEStorage networkStorage = storageService.getInventory();
        IPartitionList filter = new ItemTagPriority(TagExpParser.getMatchingOre(this.tagWhite),
                TagExpParser.getMatchingOre(this.tagBlack), this.tagWhite + this.tagBlack);
        List<GenericStack> order = new ArrayList<>();
        var counter = networkStorage.getAvailableStacks();
        int index = 0;
        for (Object2LongMap.Entry<AEKey> entry : counter) {
            if (!isCountSort && index >= CONFIG_SIZE) break;
            AEKey what = entry.getKey();
            long amount = entry.getLongValue();
            if (amount <= 0) continue;
            if (!(what instanceof AEItemKey itemKey)) continue;
            if (!filter.isListed(itemKey)) {
                continue;
            }
            if (isCountSort) {
                order.add(new GenericStack(itemKey, amount));
            } else {
                long request = networkStorage.extract(what, amount, Actionable.SIMULATE, actionSource);
                if (request == 0) continue;
                // Ensure that it is valid to configure with this stack
                var slot = this.aeItemHandler.getInventory()[index];
                slot.setConfig(new GenericStack(what, 1));
                slot.setStock(new GenericStack(what, request));
                index++;
            }
        }
        if (isCountSort) {
            order.sort((o1, o2) -> Long.compare(o2.amount(), o1.amount()));
            int len = Math.min(order.size(), CONFIG_SIZE);
            for (int i = 0; i < len; i++) {
                GenericStack stack = order.get(i);
                long request = networkStorage.extract(stack.what(), stack.amount(), Actionable.SIMULATE, actionSource);
                if (request == 0) continue;
                // Ensure that it is valid to configure with this stack
                var slot = this.aeItemHandler.getInventory()[index];
                slot.setConfig(new GenericStack(stack.what(), 1));
                slot.setStock(new GenericStack(stack.what(), request));
                index++;
            }
        }
        aeItemHandler.clearInventory(index);
    }

    @Override
    protected CompoundTag writeConfigToTag() {
        CompoundTag tag = new CompoundTag();
        tag.putByte("GhostCircuit",
                (byte) IntCircuitBehaviour.getCircuitConfiguration(circuitInventory.getStackInSlot(0)));
        tag.putString("TagWhite", tagWhite);
        tag.putString("TagBlack", tagBlack);
        return tag;
    }

    @Override
    protected void readConfigFromTag(CompoundTag tag) {
        if (tag.contains("GhostCircuit")) {
            circuitInventory.setStackInSlot(0, IntCircuitBehaviour.stack(tag.getByte("GhostCircuit")));
        }

        if (tag.contains("TagWhite")) {
            tagWhite = tag.getString("TagWhite");
        }

        if (tag.contains("TagBlack")) {
            tagBlack = tag.getString("TagBlack");
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    private class FilterIFancyConfigurator implements IFancyConfigurator {

        @Override
        public Component getTitle() {
            return Component.literal("标签过滤配置");
        }

        @Override
        public IGuiTexture getIcon() {
            return GuiTextures.BUTTON_BLACKLIST.getSubTexture(0, 0, 20, 20);
        }

        @Override
        public Widget createConfigurator() {
            return new WidgetGroup(0, 0, 132, 100)
                    .addWidget(new LabelWidget(9, 4,
                            () -> "标签白名单"))
                    .addWidget(new TextFieldWidget(9, 16, 114, 16,
                            () -> tagWhite,
                            v -> tagWhite = v))
                    .addWidget(new LabelWidget(9, 36,
                            () -> "标签黑名单"))
                    .addWidget(new TextFieldWidget(9, 48, 114, 16,
                            () -> tagBlack,
                            v -> tagBlack = v))
                    .addWidget(new LabelWidget(0, 68,
                            () -> "* 表示通配符 ()表示优先"))
                    .addWidget(new LabelWidget(0, 84,
                            () -> "& = 逻辑与 | = 逻辑或 ^ = 逻辑异或"));
        }
    }

    private class ExportOnlyAEStockingItemList extends ExportOnlyAEItemList {

        public ExportOnlyAEStockingItemList(MetaMachine holder, int slots) {
            super(holder, slots, ExportOnlyAEStockingItemSlot::new);
        }

        @Override
        public boolean isStocking() {
            return true;
        }

        @Override
        public boolean isAutoPull() {
            // only read from the network, cant config this slot
            return true;
        }
    }

    private class ExportOnlyAEStockingItemSlot extends ExportOnlyAEItemSlot {

        public ExportOnlyAEStockingItemSlot() {
            super();
        }

        public ExportOnlyAEStockingItemSlot(@Nullable GenericStack config, @Nullable GenericStack stock) {
            super(config, stock);
        }

        public ItemStack extractItem(int slot, int amount, boolean simulate, boolean notifyChanges) {
            if (slot == 0 && this.stock != null) {
                if (this.config != null) {
                    // Extract the items from the real net to either validate (simulate)
                    // or extract (modulate) when this is called
                    if (!isOnline()) return ItemStack.EMPTY;
                    IGrid grid = getMainNode().getGrid();
                    if (grid == null) return ItemStack.EMPTY;
                    MEStorage aeNetwork = grid.getStorageService().getInventory();
                    Actionable action = simulate ? Actionable.SIMULATE : Actionable.MODULATE;
                    var key = config.what();
                    long extracted = aeNetwork.extract(key, amount, action, actionSource);
                    if (extracted > 0) {
                        ItemStack resultStack = key instanceof AEItemKey itemKey ? itemKey.toStack((int) extracted) : ItemStack.EMPTY;
                        if (!simulate) {
                            // may as well update the display here
                            this.stock = ExportOnlyAESlot.copy(stock, stock.amount() - extracted);
                            if (this.stock.amount() == 0) {
                                this.stock = null;
                            }
                            if (notifyChanges && this.onContentsChanged != null) {
                                this.onContentsChanged.run();
                            }
                        }
                        return resultStack;
                    }
                }
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ExportOnlyAEStockingItemSlot copy() {
            return new ExportOnlyAEStockingItemSlot(this.config == null ? null : copy(this.config), this.stock == null ? null : copy(this.stock));
        }
    }

    private static class ItemTagPriority implements IPartitionList {

        private final Set<TagKey<?>> whiteSet;
        private final Set<TagKey<?>> blackSet;
        private final String tagExp;
        private final Reference2BooleanMap<Object> memory = new Reference2BooleanOpenHashMap<>();

        public ItemTagPriority(Set<TagKey<?>> whiteSet, Set<TagKey<?>> blackSet, String tagExp) {
            this.whiteSet = whiteSet;
            this.blackSet = blackSet;
            this.tagExp = tagExp;
        }

        @Override
        public boolean isListed(AEKey aeKey) {
            Object key = aeKey.getPrimaryKey();
            return this.memory.computeIfAbsent(key, this::eval);
        }

        @Override
        public boolean isEmpty() {
            return tagExp.isEmpty();
        }

        @Override
        public Iterable<AEKey> getItems() {
            return List.of();
        }

        private boolean eval(@NotNull Object obj) {
            Holder<?> refer = null;
            if (obj instanceof Item item) {
                refer = ForgeRegistries.ITEMS.getHolder(item).orElse(null);
            } else if (obj instanceof Fluid) {
                return false;
            }

            if (refer != null) {
                if (this.whiteSet.isEmpty()) {
                    return false;
                }

                boolean pass = refer.tags().anyMatch(whiteSet::contains);
                if (pass) {
                    if (!this.blackSet.isEmpty()) {
                        return refer.tags().noneMatch(blackSet::contains);
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
