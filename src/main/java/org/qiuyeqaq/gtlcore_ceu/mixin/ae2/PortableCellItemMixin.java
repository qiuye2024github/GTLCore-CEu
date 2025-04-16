package org.qiuyeqaq.gtlcore_ceu.mixin.ae2;

import appeng.items.storage.StorageTier;
import appeng.items.tools.powered.PortableCellItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortableCellItem.class)
public class PortableCellItemMixin {

    @Shadow(remap = false)
    @Final
    private StorageTier tier;

    @Inject(method = "getBytes", at = @At("HEAD"), remap = false, cancellable = true)
    public void getBytes(ItemStack cellItem, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.tier.bytes());
    }
}
