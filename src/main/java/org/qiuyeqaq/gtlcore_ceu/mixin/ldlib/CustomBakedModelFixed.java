package org.qiuyeqaq.gtlcore_ceu.mixin.ldlib;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import com.google.common.collect.Table;
import com.llamalad7.mixinextras.sugar.Local;
import com.lowdragmc.lowdraglib.client.model.custommodel.Connections;
import com.lowdragmc.lowdraglib.client.model.custommodel.CustomBakedModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(CustomBakedModel.class)
public abstract class CustomBakedModelFixed {

    @Shadow(remap = false)
    @Final
    private Table<Direction, Connections, List<BakedQuad>> sideCache;

    @Inject(method = "getCustomQuads", at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;"), remap = false, cancellable = true)
    public void getCustomQuadsHook(BlockAndTintGetter level, BlockPos pos, BlockState state, Direction side, RandomSource rand, CallbackInfoReturnable<List<BakedQuad>> cir, @Local Connections connections) {
        var cache = sideCache.get(side, connections);
        if (cache == null) {
            cache = sideCache.values().stream().filter(Objects::nonNull)
                    .findAny().orElse(List.of());
        }
        cir.setReturnValue(cache);
    }
}
