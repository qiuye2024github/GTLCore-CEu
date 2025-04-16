package org.qiuyeqaq.gtlcore_ceu;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBedrockFluids;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBlocks;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuCovers;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.data.recipe.misc.CircuitRecipes;
import com.gregtechceu.gtceu.data.recipe.misc.FuelRecipes;
import com.gregtechceu.gtceu.data.recipe.serialized.chemistry.MixerRecipes;

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
        //GTLCEuSoundEntries.init();
    }

    @Override
    public void registerCovers() {
        GTLCEuCovers.init();
    }

    @Override
    public void registerElements() {
        //GTLCEuElements.init();
    }

    @Override
    public void registerTagPrefixes() {
        //GTLCEuTagPrefix.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        //GCyMRecipes.init(provider);
        FuelRecipes.init(provider);
        //MachineRecipe.init(provider);
        //Misc.init(provider);
        //ElementCopying.init(provider);
        //StoneDustProcess.init(provider);
        //Lanthanidetreatment.init(provider);
        CircuitRecipes.init(provider);
        MixerRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        //RemoveRecipe.init(consumer);
    }

    @Override
    public void registerFluidVeins() {
        if (!Platform.isDevEnv()) {
            GTLCEuBedrockFluids.init();
        }
    }
}
