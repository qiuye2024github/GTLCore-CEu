package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import com.hepdd.gtmthings.utils.TeamUtil;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.qiuyeqaq.gtlcore_ceu.api.machine.feature.IExtendWirelessEnergyContainerHolder;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.NoEnergyMultiblockMachine;
import org.qiuyeqaq.gtlcore_ceu.common.wireless.ExtendWirelessEnergyContainer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HarmonyMachine extends NoEnergyMultiblockMachine implements IExtendWirelessEnergyContainerHolder {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            HarmonyMachine.class, NoEnergyMultiblockMachine.MANAGED_FIELD_HOLDER);

    private static final Fluid HYDROGEN = GTMaterials.Hydrogen.getFluid();
    private static final Fluid HELIUM = GTMaterials.Helium.getFluid();

    @Getter
    @Setter
    private WirelessEnergyContainer WirelessEnergyContainerCache;

    @Persisted
    private int oc = 0;
    @Persisted
    private long hydrogen = 0, helium = 0;
    @Persisted
    private UUID userid;

    public @Nullable UUID getUUID() {
        return getOwnerUUID();
    }

    protected ConditionalSubscriptionHandler StartupSubs;

    public HarmonyMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.StartupSubs = new ConditionalSubscriptionHandler(this, this::StartupUpdate, this::isFormed);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    protected void StartupUpdate() {
        if (getOffsetTimer() % 20 == 0) {
            oc = 0;
        }
    }

    private long getStartupEnergy() {
        return oc == 0 ? 0 : (long) (5277655810867200L * Math.pow(8, oc - 1));
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        StartupSubs.initialize(getLevel());
    }

    @Nullable
    @Override
    protected GTRecipe getRealRecipe(GTRecipe recipe) {
        if (getUUID() != null && hydrogen >= 1024000000 && helium >= 1024000000 && oc > 0) {
            hydrogen -= 1024000000;
            helium -= 1024000000;
            ExtendWirelessEnergyContainer container = getWirelessEnergyContainer();
            long energy = getStartupEnergy() * Math.max(1, (recipe.data.getInt("tier") - 1) << 2);
            if (container != null && container.unrestrictedRemoveEnergy(energy) == energy) {
                recipe.duration = (recipe.duration << 2) / (1 << (oc));
                return recipe;
            }
        }
        return null;
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        if (this.userid == null || !this.userid.equals(player.getUUID())) {
            this.userid = player.getUUID();
        }
        return true;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (this.isFormed()) {
            if (getUUID() != null) {
                ExtendWirelessEnergyContainer container = getWirelessEnergyContainer();
                textList.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.0",
                        TeamUtil.GetName(getLevel(), getUUID())));
                if (container != null) textList.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.1",
                        FormattingUtil.formatNumbers(container.getStorage())));
            }
            textList.add(Component.literal("启动耗能：" + FormattingUtil.formatNumbers(getStartupEnergy()) + "EU"));
            textList.add(Component.literal("氢储量：" + FormattingUtil.formatNumbers(hydrogen) + "mb"));
            textList.add(Component.literal("氦储量：" + FormattingUtil.formatNumbers(helium) + "mb"));
        }
    }
}
