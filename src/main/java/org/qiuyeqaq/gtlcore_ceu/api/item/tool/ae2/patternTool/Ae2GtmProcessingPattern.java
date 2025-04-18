package org.qiuyeqaq.gtlcore_ceu.api.item.tool.ae2.patternTool;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class Ae2GtmProcessingPattern extends Ae2BaseProcessingPattern {

    public final GTRecipe recipe;

    public Ae2GtmProcessingPattern(ItemStack patternStack, ServerPlayer serverPlayer, GTRecipe recipe) {
        super(patternStack, serverPlayer);
        this.recipe = recipe;
    }
    // 将模头，模具加入忽略名单
    // this.PATTERNIGNOREITEMS.addAll(Arrays.stream(GTItems.SHAPE_MOLDS).map(ItemProviderEntry::asItem).toList());
    // this.PATTERNIGNOREITEMS.addAll(Arrays.stream(GTItems.SHAPE_EXTRUDERS).map(ItemProviderEntry::asItem).toList());
}
