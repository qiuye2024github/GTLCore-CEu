package org.qiuyeqaq.gtlcore_ceu.mixin.gtm.ftb;

import dev.ftb.mods.ftbchunks.net.TeleportFromMapPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TeleportFromMapPacket.class)
public class TeleportFromMapPacketMixin {

    @ModifyConstant(method = "handle", constant = @Constant(intValue = 2, ordinal = 0), remap = false)
    public int permissionModify(int constant) {
        return 0;
    }
}
