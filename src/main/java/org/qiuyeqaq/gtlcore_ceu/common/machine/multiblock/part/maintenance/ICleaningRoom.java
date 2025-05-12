package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part.maintenance;

import com.google.common.collect.ImmutableSet;
import com.gregtechceu.gtceu.api.capability.ICleanroomReceiver;
import com.gregtechceu.gtceu.api.machine.feature.ICleanroomProvider;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.multiblock.DummyCleanroom;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.GTLCEuCleanroomType;

import java.util.Set;

public class ICleaningRoom {

    /**
     * 超净
     */
    private static final Set<CleanroomType> CLEANROOM = new ObjectOpenHashSet<>();

    /**
     * 无菌
     */
    private static final Set<CleanroomType> STERILE_CLEANROOM = new ObjectOpenHashSet<>();

    /**
     * 绝对超净
     */
    private static final Set<CleanroomType> LAW_CLEANROOM = new ObjectOpenHashSet<>();

    static {
        CLEANROOM.add(CleanroomType.CLEANROOM);
        STERILE_CLEANROOM.addAll(CLEANROOM);
        STERILE_CLEANROOM.add(CleanroomType.STERILE_CLEANROOM);
        LAW_CLEANROOM.addAll(STERILE_CLEANROOM);
        LAW_CLEANROOM.add(GTLCEuCleanroomType.LAW_CLEANROOM);
    }

    public static final DummyCleanroom DUMMY_CLEANROOM = DummyCleanroom.createForTypes(CLEANROOM);
    public static final DummyCleanroom STERILE_DUMMY_CLEANROOM = DummyCleanroom.createForTypes(STERILE_CLEANROOM);
    public static final DummyCleanroom LAW_DUMMY_CLEANROOM = DummyCleanroom.createForTypes(LAW_CLEANROOM);

    public static void addedToController(IMultiController controller, ICleanroomProvider cleanroomTypes) {
        if (controller instanceof ICleanroomReceiver receiver) {
            receiver.setCleanroom(cleanroomTypes);
        }
    }

    public static void removedFromController(IMultiController controller, ICleanroomProvider cleanroomTypes) {
        if (controller instanceof ICleanroomReceiver receiver && receiver.getCleanroom() == cleanroomTypes) {
            receiver.setCleanroom(null);
        }
    }

    public static ImmutableSet<CleanroomType> getCleanroomTypes(ICleanroomProvider p) {
        return ImmutableSet.copyOf(p.getTypes());
    }
}
