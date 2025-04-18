package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMaterials.*;

public class CircuitRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        AUTOCLAVE_RECIPES.recipeBuilder("sterilized_petri_dish")
                .inputItems(GTItems.PETRI_DISH)
                .inputFluids(AbsoluteEthanol.getFluid(100))
                .outputItems(GTLCEuItems.STERILIZED_PETRI_DISH)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .duration(25).EUt(7680).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("electricaly_wired_petri_dish")
                .inputItems(GTLCEuItems.STERILIZED_PETRI_DISH)
                .inputItems(TagPrefix.wireFine, GTMaterials.Titanium)
                .inputFluids(GTMaterials.Polyethylene.getFluid(1296))
                .outputItems(GTLCEuItems.ELECTRICALY_WIRED_PETRI_DISH)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .duration(100).EUt(7680).save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder("petri_dish")
                .inputItems(GTLCEuItems.CONTAMINATED_PETRI_DISH)
                .outputItems(GTItems.PETRI_DISH)
                .inputFluids(PiranhaSolution.getFluid(100))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(25).EUt(30).save(provider);
    }
}
