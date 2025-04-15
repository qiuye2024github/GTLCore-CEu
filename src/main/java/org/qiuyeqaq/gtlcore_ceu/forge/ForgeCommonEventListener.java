package org.qiuyeqaq.gtlcore_ceu.forge;

import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.MissingMappingsEvent;
import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.qiuyeqaq.gtlcore_ceu.common.item.GTLCEuItems;

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
    public static void remapIds(MissingMappingsEvent event) {
        event.getMappings(Registries.BLOCK, KubeJS.MOD_ID).forEach(mapping -> {
        });
        event.getMappings(Registries.ITEM, "infinitycells").forEach(mapping -> {
        });
    }
}
