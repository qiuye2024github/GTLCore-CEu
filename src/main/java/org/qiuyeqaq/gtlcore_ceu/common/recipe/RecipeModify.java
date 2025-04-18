package org.qiuyeqaq.gtlcore_ceu.common.recipe;

import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMaterials;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeTypes;
import org.qiuyeqaq.gtlcore_ceu.data.recipe.GenerateDisassembly;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

public class RecipeModify {

    public static void init() {
        GTRecipeTypes.SIFTER_RECIPES.setMaxIOSize(1, 6, 1, 1);
        GTRecipeTypes.ASSEMBLER_RECIPES.onRecipeBuild(GenerateDisassembly::generateDisassembly);
        GTRecipeTypes.ASSEMBLER_RECIPES.onRecipeBuild(GenerateDisassembly::generateDisassembly);
        GTRecipeTypes.PLASMA_GENERATOR_FUELS.onRecipeBuild((recipeBuilder, provider) -> {
            long eu = recipeBuilder.duration * GTValues.V[GTValues.EV];
            GTLCEuRecipeTypes.HEAT_EXCHANGER_RECIPES.recipeBuilder(recipeBuilder.id)
                    .inputFluids(FluidRecipeCapability.CAP.of(recipeBuilder.input
                            .get(FluidRecipeCapability.CAP).get(0).getContent()))
                    .inputFluids(GTMaterials.DistilledWater.getFluid(Math.toIntExact(eu / 160)))
                    .outputFluids(FluidRecipeCapability.CAP.of(recipeBuilder.output
                            .get(FluidRecipeCapability.CAP).get(0).getContent()))
                    .outputFluids(GTLCEuMaterials.SupercriticalSteam.getFluid(Math.toIntExact(eu)))
                    .addData("eu", eu)
                    .duration(200)
                    .save(provider);
        });

        GTRecipeTypes.LASER_ENGRAVER_RECIPES.onRecipeBuild((recipeBuilder, provider) -> {
            GTRecipeBuilder recipe = GTLCEuRecipeTypes.DIMENSIONAL_FOCUS_ENGRAVING_ARRAY_RECIPES.copyFrom(recipeBuilder)
                    .duration((int) (recipeBuilder.duration * 0.2))
                    .EUt(recipeBuilder.EUt() * 4);
            double value = Math.log10(recipeBuilder.EUt()) / Math.log10(4);
            if (value > 10) {
                recipe.inputFluids(GTLCEuMaterials.EuvPhotoresist.getFluid(Math.toIntExact((long) (value / 2))));
            } else {
                recipe.inputFluids(GTLCEuMaterials.Photoresist.getFluid(Math.toIntExact((long) value)));
            }
            recipe.save(provider);
        });
    }
}
