package org.qiuyeqaq.gtlcore_ceu.forge;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;
import org.qiuyeqaq.gtlcore_ceu.config.ConfigHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = GTLCore_CEu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEventListener {

    @SubscribeEvent
    public static void onPortalSpawnEvent(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity().level().getBlockState(event.getPos()).getBlock() == Blocks.END_PORTAL_FRAME &&
                event.getEntity().getItemInHand(event.getHand()).getItem() == Items.ENDER_EYE) {
            if (event.getEntity() instanceof ServerPlayer player &&
                    Objects.equals(player.getOffhandItem(), "kubejs:end_data")) {
                player.getOffhandItem().setCount(player.getOffhandItem().getCount() - 1);
                return;
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onEnderTeleportEvent(EntityTeleportEvent.EnderEntity event) {
        if (event.getEntity() instanceof EnderMan || event.getEntity() instanceof Shulker) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (ConfigHolder.INSTANCE.disableDrift && event.phase == TickEvent.Phase.END &&
                event.side == LogicalSide.CLIENT && event.player.xxa == 0 && event.player.zza == 0) {
            event.player.setDeltaMovement(event.player.getDeltaMovement().multiply(0.5, 1, 0.5));
        }
    }

    @SubscribeEvent
    public static void remapIds(MissingMappingsEvent event) {
        /*
         * event.getMappings(Registries.BLOCK, KubeJS.MOD_ID).forEach(mapping -> {
         * if (mapping.getKey().equals(KubeJS.id("multi_functional_casing"))) {
         * mapping.remap(GTLCEuBlocks.MULTI_FUNCTIONAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("create_casing"))) {
         * mapping.remap(GTLCEuBlocks.CREATE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("space_elevator_mechanical_casing"))) {
         * mapping.remap(GTLCEuBlocks.SPACE_ELEVATOR_MECHANICAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("manipulator"))) {
         * mapping.remap(GTLCEuBlocks.MANIPULATOR.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("blaze_blast_furnace_casing"))) {
         * mapping.remap(GTLCEuBlocks.BLAZE_BLAST_FURNACE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("cold_ice_casing"))) {
         * mapping.remap(GTLCEuBlocks.COLD_ICE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("dimension_connection_casing"))) {
         * mapping.remap(GTLCEuBlocks.DIMENSION_CONNECTION_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("molecular_casing"))) {
         * mapping.remap(GTLCEuBlocks.MOLECULAR_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("dimension_injection_casing"))) {
         * mapping.remap(GTLCEuBlocks.DIMENSION_INJECTION_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("dimensionally_transcendent_casing"))) {
         * mapping.remap(GTLCEuBlocks.DIMENSIONALLY_TRANSCENDENT_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("echo_casing"))) {
         * mapping.remap(GTLCEuBlocks.ECHO_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("dragon_strength_tritanium_casing"))) {
         * mapping.remap(GTLCEuBlocks.DRAGON_STRENGTH_TRITANIUM_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("aluminium_bronze_casing"))) {
         * mapping.remap(GTLCEuBlocks.ALUMINIUM_BRONZE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("antifreeze_heatproof_machine_casing"))) {
         * mapping.remap(GTLCEuBlocks.ANTIFREEZE_HEATPROOF_MACHINE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("enhance_hyper_mechanical_casing"))) {
         * mapping.remap(GTLCEuBlocks.ENHANCE_HYPER_MECHANICAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("extreme_strength_tritanium_casing"))) {
         * mapping.remap(GTLCEuBlocks.EXTREME_STRENGTH_TRITANIUM_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("graviton_field_constraint_casing"))) {
         * mapping.remap(GTLCEuBlocks.GRAVITON_FIELD_CONSTRAINT_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("hyper_mechanical_casing"))) {
         * mapping.remap(GTLCEuBlocks.HYPER_MECHANICAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("iridium_casing"))) {
         * mapping.remap(GTLCEuBlocks.IRIDIUM_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("lafium_mechanical_casing"))) {
         * mapping.remap(GTLCEuBlocks.LAFIUM_MECHANICAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("oxidation_resistant_hastelloy_n_mechanical_casing"))) {
         * mapping.remap(GTLCEuBlocks.OXIDATION_RESISTANT_HASTELLOY_N_MECHANICAL_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("pikyonium_machine_casing"))) {
         * mapping.remap(GTLCEuBlocks.PIKYONIUM_MACHINE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("sps_casing"))) {
         * mapping.remap(GTLCEuBlocks.SPS_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("naquadah_alloy_casing"))) {
         * mapping.remap(GTLCEuBlocks.NAQUADAH_ALLOY_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("process_machine_casing"))) {
         * mapping.remap(GTLCEuBlocks.PROCESS_MACHINE_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("fission_reactor_casing"))) {
         * mapping.remap(GTLCEuBlocks.FISSION_REACTOR_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("degenerate_rhenium_constrained_casing"))) {
         * mapping.remap(GTLCEuBlocks.DEGENERATE_RHENIUM_CONSTRAINED_CASING.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("infinity_glass"))) {
         * mapping.remap(GTLCEuBlocks.INFINITY_GLASS.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("rhenium_reinforced_energy_glass"))) {
         * mapping.remap(GTLCEuBlocks.RHENIUM_REINFORCED_ENERGY_GLASS.get());
         * }
         * if (mapping.getKey().equals(KubeJS.id("hsss_reinforced_borosilicate_glass"))) {
         * mapping.remap(GTLCEuBlocks.HSSS_REINFORCED_BOROSILICATE_GLASS.get());
         * }
         * });
         */
        event.getMappings(Registries.ITEM, "infinitycells").forEach(mapping -> {
            if (mapping.getKey().equals(new ResourceLocation("infinitycells:infinity_cell"))) {
                mapping.remap(GTLCEuItems.ITEM_INFINITY_CELL.get());
            }
            if (mapping.getKey().equals(new ResourceLocation("infinitycells:infinity_fluid_cell"))) {
                mapping.remap(GTLCEuItems.FLUID_INFINITY_CELL.get());
            }
        });
    }
}
