package org.qiuyeqaq.gtlcore_ceu;

import org.qiuyeqaq.gtlcore_ceu.api.data.tag.GTLCEuTagPrefix;
import org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration;
import org.qiuyeqaq.gtlcore_ceu.common.data.*;
import org.qiuyeqaq.gtlcore_ceu.data.recipe.*;
import org.qiuyeqaq.gtlcore_ceu.data.recipe.chemistry.MixerRecipes;
import org.qiuyeqaq.gtlcore_ceu.data.recipe.processing.Lanthanidetreatment;
import org.qiuyeqaq.gtlcore_ceu.data.recipe.processing.StoneDustProcess;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.lowdragmc.lowdraglib.Platform;

import java.util.function.Consumer;

@GTAddon
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
    public void registerSounds() {
        GTLCEuSoundEntries.init();
    }

    @Override
    public void registerCovers() {
        GTLCEuCovers.init();
    }

    @Override
    public void registerElements() {
        GTLCEuElements.init();
    }

    @Override
    public void registerTagPrefixes() {
        GTLCEuTagPrefix.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GCyMRecipes.init(provider);
        FuelRecipes.init(provider);
        MachineRecipe.init(provider);
        Misc.init(provider);
        ElementCopying.init(provider);
        StoneDustProcess.init(provider);
        Lanthanidetreatment.init(provider);
        CircuitRecipes.init(provider);
        MixerRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        RemoveRecipe.init(consumer);
    }

    @Override
    public void registerFluidVeins() {
        if (!Platform.isDevEnv()) {
            GTLCEuBedrockFluids.init();
        }
    }
}
