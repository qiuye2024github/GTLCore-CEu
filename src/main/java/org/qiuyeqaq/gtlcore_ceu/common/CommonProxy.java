package org.qiuyeqaq.gtlcore_ceu.common;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;
import org.qiuyeqaq.gtlcore_ceu.common.data.*;
import org.qiuyeqaq.gtlcore_ceu.config.ConfigHolder;
import org.qiuyeqaq.gtlcore_ceu.integration.ae2.InfinityCellGuiHandler;
import org.qiuyeqaq.gtlcore_ceu.integration.ae2.storage.InfinityCellHandler;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import appeng.api.storage.StorageCells;
import appeng.core.AELog;

import static org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration.REGISTRATE;

public class CommonProxy {

    public CommonProxy() {
        CommonProxy.init();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(eventBus);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::addMaterialRegistries);
        eventBus.addListener(this::addMaterials);
        eventBus.addListener(this::modifyMaterials);
        eventBus.addGenericListener(RecipeConditionType.class, this::registerRecipeConditions);
        eventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        eventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
    }

    public static void init() {
        GTLCEuCreativeModeTabs.init();
        ConfigHolder.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        StorageCells.addCellHandler(InfinityCellHandler.INSTANCE);
        StorageCells.addCellGuiHandler(new InfinityCellGuiHandler());
        event.enqueueWork(this::postRegistrationInitialization).whenComplete((res, err) -> {
            if (err != null) {
                AELog.warn(err);
            }
        });
    }

    public void postRegistrationInitialization() {
        GTLCEuItems.InitUpgrades();
    }

    private void clientSetup(final FMLClientSetupEvent event) {}

    // You MUST have this for custom materials.
    // Remember to register them not to GT's namespace, but your own.
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(GTLCore_CEu.MOD_ID);
    }

    // As well as this.
    private void addMaterials(MaterialEvent event) {
        GTLCEuMaterials.init();
    }

    // This is optional, though.
    private void modifyMaterials(PostMaterialEvent event) {}

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTLCEuRecipeTypes.init();
    }

    private void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType<?>> event) {
        GTLCEuRecipeConditions.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTLCEuMachines.init();
        // GeneratorMachine.LARGE_SEMI_FLUID_GENERATOR.setRecipeTypes(new GTRecipeType[] {
        // GTLCEuRecipeTypes.SEMI_FLUID_GENERATOR_FUELS });
    }
}
