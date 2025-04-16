package org.qiuyeqaq.gtlcore_ceu.common.data;

import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import org.qiuyeqaq.gtlcore_ceu.common.recipe.condition.GravityCondition;

public class GTLCEuRecipeConditions {

    private GTLCEuRecipeConditions() {}

    public static final RecipeConditionType<GravityCondition> GRAVITY = GTRegistries.RECIPE_CONDITIONS.register("gravity",
            new RecipeConditionType<>(GravityCondition::new, GravityCondition.CODEC));

    public static void init() {}
}
