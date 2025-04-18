package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GCyMRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        registerMultiblockControllerRecipes(provider);
        registerPartsRecipes(provider);
    }

    private static void registerMultiblockControllerRecipes(Consumer<FinishedRecipe> provider) {}

    private static void registerPartsRecipes(Consumer<FinishedRecipe> provider) {}
}
