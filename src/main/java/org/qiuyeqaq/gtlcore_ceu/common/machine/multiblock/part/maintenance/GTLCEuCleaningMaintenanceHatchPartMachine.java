package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part.maintenance;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.ICleanroomReceiver;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ICleanroomProvider;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.common.machine.multiblock.part.AutoMaintenanceHatchPartMachine;
import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GTLCEuCleaningMaintenanceHatchPartMachine extends AutoMaintenanceHatchPartMachine {

    ICleanroomProvider cleanroomTypes;

    public GTLCEuCleaningMaintenanceHatchPartMachine(IMachineBlockEntity metaTileEntityId,
                                                  ICleanroomProvider cleanroomTypes) {
        super(metaTileEntityId);
        this.cleanroomTypes = cleanroomTypes;
    }

    @Override
    public void addedToController(IMultiController controller) {
        super.addedToController(controller);
        if (controller instanceof ICleanroomReceiver receiver) {
            receiver.setCleanroom(cleanroomTypes);
        }
    }

    @Override
    public void removedFromController(IMultiController controller) {
        super.removedFromController(controller);
        if (controller instanceof ICleanroomReceiver receiver && receiver.getCleanroom() == cleanroomTypes) {
            receiver.setCleanroom(null);
        }
    }

    @Override
    public int getTier() {
        if (this.cleanroomTypes == ICleaningRoom.STERILE_DUMMY_CLEANROOM) return GTValues.ZPM;
        if (this.cleanroomTypes == ICleaningRoom.LAW_DUMMY_CLEANROOM) return GTValues.UEV;
        return GTValues.HV;
    }
}
