package org.qiuyeqaq.gtlcore_ceu.mixin.ae2;

import appeng.api.storage.cells.IBasicCellItem;
import appeng.api.storage.cells.ISaveProvider;
import appeng.me.cells.BasicCellInventory;
import net.minecraft.world.item.ItemStack;
import org.qiuyeqaq.gtlcore_ceu.config.ConfigHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasicCellInventory.class)
public class BasicCellInventoryMixin {

    @Shadow(remap = false)
    private int maxItemTypes;

    @Shadow(remap = false)
    @Final
    private IBasicCellItem cellType;

    @Shadow(remap = false)
    @Final
    private ItemStack i;

    @Inject(method = "<init>", at = @At("RETURN"), remap = false)
    private void BasicCellInventory(IBasicCellItem cellType, ItemStack o, ISaveProvider container, CallbackInfo ci) {
        this.maxItemTypes = this.cellType.getTotalTypes(this.i) * ConfigHolder.INSTANCE.cellType;
    }

    @Inject(method = "getBytesPerType", at = @At("HEAD"), remap = false, cancellable = true)
    public void getBytesPerType(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
    }
}
