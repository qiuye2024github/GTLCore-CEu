package org.qiuyeqaq.gtlcore_ceu.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class MachineUtil {

    private MachineUtil() {
        throw new IllegalAccessError();
    }

    public static BlockPos getOffsetPos(int a, int b, Direction facing, BlockPos pos) {
        int x = 0, z = 0;
        switch (facing) {
            case NORTH -> z = a;
            case SOUTH -> z = -a;
            case WEST -> x = a;
            case EAST -> x = -a;
        }
        return pos.offset(x, b, z);
    }
}
