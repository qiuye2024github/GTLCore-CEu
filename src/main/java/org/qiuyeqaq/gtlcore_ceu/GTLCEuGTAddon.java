package org.qiuyeqaq.gtlcore_ceu;

import org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBedrockFluids;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBlocks;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.data.recipe.misc.CircuitRecipes;
import com.gregtechceu.gtceu.data.recipe.misc.FuelRecipes;
import com.gregtechceu.gtceu.data.recipe.serialized.chemistry.MixerRecipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.lowdragmc.lowdraglib.Platform;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;

import java.util.function.Consumer;

public class GTLCEuGTAddon implements IGTAddon {

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
    public void initializeAddon() {
        GTLCEuItems.init();
        GTLCEuBlocks.init();
    }

    @Override
    public void registerSounds() {}

    @Override
    public void registerCovers() {}

    @Override
    public void registerElements() {}

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        FuelRecipes.init(provider);
        CircuitRecipes.init(provider);
        MixerRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {}

    @Override
    public void registerFluidVeins() {
        if (!Platform.isDevEnv()) {
            GTLCEuBedrockFluids.init();
        }
    }
}
