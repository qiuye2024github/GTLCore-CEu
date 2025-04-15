package org.qiuyeqaq.gtlcore_ceu.mixin.ftbu;

import org.qiuyeqaq.gtlcore_ceu.config.ConfigHolder;
import org.qiuyeqaq.gtlcore_ceu.utils.TextUtil;

import net.minecraft.world.item.Item;

import committee.nova.mods.avaritia.api.utils.ItemUtils;
import dev.ftb.mods.ftbultimine.FTBUltiminePlayerData;
import dev.ftb.mods.ftbultimine.shape.BlockMatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FTBUltiminePlayerData.class)
public class YouCantBreakAE2Mixin {

    @ModifyVariable(method = "updateBlocks", at = @At(value = "STORE", ordinal = 2), remap = false)
    public BlockMatcher modifyBlockMatcher(BlockMatcher matcher) {
        if (ConfigHolder.INSTANCE.blackBlockList == null || ConfigHolder.INSTANCE.blackBlockList.length < 1) {
            return matcher;
        }
        return (original, state) -> {
            boolean flag = !TextUtil.containsWithWildcard(ConfigHolder.INSTANCE.blackBlockList,
                    ItemUtils.getId(Item.byBlock(state.getBlock())));
            return flag && state.getBlock() == original.getBlock();
        };
    }
}
