package org.qiuyeqaq.gtlcore_ceu;

import org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBedrockFluids;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.lowdragmc.lowdraglib.Platform;

import java.util.function.Consumer;

public class GTLCEuAddon implements IGTAddon {

    @Override
    public String addonModId() {
        return GTLCore_CEu.MOD_ID;
    }

    @Override
    public GTRegistrate getRegistrate() {
        return GTLCEuRegistration.REGISTRATE;
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public void registerSounds() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {}

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {}

    @Override
    public void registerFluidVeins() {
        if (!Platform.isDevEnv()) {
            GTLCEuBedrockFluids.init();
        }
    }
}
