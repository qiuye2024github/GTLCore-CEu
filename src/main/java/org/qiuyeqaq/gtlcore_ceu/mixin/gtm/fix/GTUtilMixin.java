package org.qiuyeqaq.gtlcore_ceu.mixin.gtm.fix;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.utils.GTUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GTUtil.class)
public class GTUtilMixin {

    /**
     * @author qiuyeqaq
     * @reason fix
     */
    @Overwrite(remap = false)
    public static byte getFloorTierByVoltage(long voltage) {
        return (byte) Math.max(GTValues.ULV, GTUtil.nearestLesserOrEqual(GTValues.V, voltage));
    }
}
