package org.qiuyeqaq.gtlcore_ceu.mixin.gtm;

import com.gregtechceu.gtceu.integration.jei.multipage.MultiblockInfoCategory;
import com.lowdragmc.lowdraglib.Platform;
import mezz.jei.api.registration.IRecipeRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiblockInfoCategory.class)
public class MultiblockInfoCategoryMixin {

    @Inject(method = "registerRecipes", at = @At("HEAD"), cancellable = true, remap = false)
    private static void registerRecipes(IRecipeRegistration registry, CallbackInfo ci) {
        if (Platform.isDevEnv()) {
            ci.cancel();
        }
    }
}
