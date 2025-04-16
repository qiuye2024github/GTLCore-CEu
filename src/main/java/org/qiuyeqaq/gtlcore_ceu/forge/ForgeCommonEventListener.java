package org.qiuyeqaq.gtlcore_ceu.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.LogicalSide;
import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;

import dev.latvian.mods.kubejs.KubeJS;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;
import org.qiuyeqaq.gtlcore_ceu.config.ConfigHolder;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = GTLCore_CEu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEventListener {

    @SubscribeEvent
    public static void onPortalSpawnEvent(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
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
        event.getMappings(Registries.BLOCK, KubeJS.MOD_ID).forEach(mapping -> {
        });
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
