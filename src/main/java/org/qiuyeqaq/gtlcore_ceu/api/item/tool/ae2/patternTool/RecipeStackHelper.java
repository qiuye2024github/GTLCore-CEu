package org.qiuyeqaq.gtlcore_ceu.api.item.tool.ae2.patternTool;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;

public class RecipeStackHelper {

    final GTRecipe recipe;

    protected RecipeStackHelper(GTRecipe recipe) {
        this.recipe = recipe;
    }

    public static String getFluidTranslatedName(FluidStack fluidStack) {
        return fluidStack.getDisplayName().getString() + "(" + Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluidStack.getFluid())) + " " + fluidStack.getAmount() + ") ";
    }
}
