package org.qiuyeqaq.gtlcore_ceu.mixin.gtm;

import com.gregtechceu.gtceu.integration.ae2.machine.MEStockingBusPartMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MEStockingBusPartMachine.class)
public class MEStockingBusPartMachineMixin {

    @ModifyArg(method = "syncME",
            at = @At(value = "INVOKE",
                    target = "Lappeng/api/storage/MEStorage;extract(Lappeng/api/stacks/AEKey;JLappeng/api/config/Actionable;Lappeng/api/networking/security/IActionSource;)J"),
            index = 1,
            remap = false)
    private long modifySlotLimit(long limit) {
        return Integer.MAX_VALUE;
    }
}
