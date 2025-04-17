package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import java.util.List;
import java.util.function.Consumer;

import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeTypes.DISASSEMBLY_RECIPES;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipes.DISASSEMBLY_RECORD;

public class GenerateDisassembly {

    private static final String[] inputItem = { "suprachronal_", "_circuit", "_processor", "_assembly",
            "_computer", "_mainframe", "circuit_resonatic_" };

    private static final String[] outputItem = { "_cable", "_frame", "_fence", "_electric_motor",
            "_electric_pump", "_conveyor_module", "_electric_piston", "_robot_arm", "_field_generator",
            "_emitter", "_sensor", "smd_", "_lamp", "_crate", "_drum", "_machine_casing",
            "ae2:blank_pattern", "gtceu:carbon_nanoswarm" };

    private static boolean isExcludeItems(String id, String[] excludeItems) {
        for (String pattern : excludeItems) {
            if (id.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    public static void generateDisassembly(GTRecipeBuilder r, Consumer<FinishedRecipe> p) {
        List<Content> c = r.output.getOrDefault(ItemRecipeCapability.CAP, null);
        if (c == null) {
            GTLCore_CEu.LOGGER.atError().log("配方{}没有输出", r.id);
            return;
        }
        ItemStack[] outputs = ItemRecipeCapability.CAP
                .of(c.get(0).getContent()).getItems();
    }
}
