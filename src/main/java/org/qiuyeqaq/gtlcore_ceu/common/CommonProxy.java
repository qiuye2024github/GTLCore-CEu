package org.qiuyeqaq.gtlcore_ceu.common;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration.REGISTRATE;

public class CommonProxy {

    public CommonProxy() {
        CommonProxy.init();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(eventBus);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::addMaterialRegistries);
        eventBus.addListener(this::modifyMaterials);
    }

    public static void init() {
        ConfigHolder.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {}

    // You MUST have this for custom materials.
    // Remember to register them not to GT's namespace, but your own.
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(GTLCore_CEu.MOD_ID);
    }

    // This is optional, though.
    private void modifyMaterials(PostMaterialEvent event) {}
}
