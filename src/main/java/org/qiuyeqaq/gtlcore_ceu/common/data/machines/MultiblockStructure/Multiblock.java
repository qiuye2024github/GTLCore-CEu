package org.qiuyeqaq.gtlcore_ceu.common.data.machines.MultiblockStructure;

import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import org.qiuyeqaq.gtlcore_ceu.common.data.machines.MultiblockStructure.Multblock.Extradimensional_Part1;
import org.qiuyeqaq.gtlcore_ceu.common.data.machines.MultiblockStructure.Multblock.EyeOfHarmony_Part1;

public class Multiblock {

    public Multiblock() {}

    public static final FactoryBlockPattern EYE_OF_HARMONY;
    public static final FactoryBlockPattern EXTRADIMENSIONAL;

    static {
        EYE_OF_HARMONY = FactoryBlockPattern.start()
                .aisle(EyeOfHarmony_Part1.LAYER_001).aisle(EyeOfHarmony_Part1.LAYER_002)
                .aisle(EyeOfHarmony_Part1.LAYER_003).aisle(EyeOfHarmony_Part1.LAYER_004)
                .aisle(EyeOfHarmony_Part1.LAYER_005).aisle(EyeOfHarmony_Part1.LAYER_006)
                .aisle(EyeOfHarmony_Part1.LAYER_007).aisle(EyeOfHarmony_Part1.LAYER_008)
                .aisle(EyeOfHarmony_Part1.LAYER_009).aisle(EyeOfHarmony_Part1.LAYER_010)
                .aisle(EyeOfHarmony_Part1.LAYER_011).aisle(EyeOfHarmony_Part1.LAYER_012)
                .aisle(EyeOfHarmony_Part1.LAYER_013).aisle(EyeOfHarmony_Part1.LAYER_014)
                .aisle(EyeOfHarmony_Part1.LAYER_015).aisle(EyeOfHarmony_Part1.LAYER_016)
                .aisle(EyeOfHarmony_Part1.LAYER_018).aisle(EyeOfHarmony_Part1.LAYER_019)
                .aisle(EyeOfHarmony_Part1.LAYER_020).aisle(EyeOfHarmony_Part1.LAYER_021)
                .aisle(EyeOfHarmony_Part1.LAYER_022).aisle(EyeOfHarmony_Part1.LAYER_023)
                .aisle(EyeOfHarmony_Part1.LAYER_024).aisle(EyeOfHarmony_Part1.LAYER_025)
                .aisle(EyeOfHarmony_Part1.LAYER_026).aisle(EyeOfHarmony_Part1.LAYER_027)
                .aisle(EyeOfHarmony_Part1.LAYER_028).aisle(EyeOfHarmony_Part1.LAYER_029)
                .aisle(EyeOfHarmony_Part1.LAYER_030).aisle(EyeOfHarmony_Part1.LAYER_031)
                .aisle(EyeOfHarmony_Part1.LAYER_032).aisle(EyeOfHarmony_Part1.LAYER_033);

        EXTRADIMENSIONAL = FactoryBlockPattern.start()
                .aisle(Extradimensional_Part1.LAYER_001).aisle(Extradimensional_Part1.LAYER_002)
                .aisle(Extradimensional_Part1.LAYER_003).aisle(Extradimensional_Part1.LAYER_004)
                .aisle(Extradimensional_Part1.LAYER_005).aisle(Extradimensional_Part1.LAYER_006)
                .aisle(Extradimensional_Part1.LAYER_007).aisle(Extradimensional_Part1.LAYER_008)
                .aisle(Extradimensional_Part1.LAYER_009).aisle(Extradimensional_Part1.LAYER_010)
                .aisle(Extradimensional_Part1.LAYER_011).aisle(Extradimensional_Part1.LAYER_012)
                .aisle(Extradimensional_Part1.LAYER_013).aisle(Extradimensional_Part1.LAYER_014)
                .aisle(Extradimensional_Part1.LAYER_015).aisle(Extradimensional_Part1.LAYER_016)
                .aisle(Extradimensional_Part1.LAYER_017).aisle(Extradimensional_Part1.LAYER_018)
                .aisle(Extradimensional_Part1.LAYER_019).aisle(Extradimensional_Part1.LAYER_020)
                .aisle(Extradimensional_Part1.LAYER_021).aisle(Extradimensional_Part1.LAYER_022)
                .aisle(Extradimensional_Part1.LAYER_023).aisle(Extradimensional_Part1.LAYER_024)
                .aisle(Extradimensional_Part1.LAYER_025).aisle(Extradimensional_Part1.LAYER_026)
                .aisle(Extradimensional_Part1.LAYER_027).aisle(Extradimensional_Part1.LAYER_028)
                .aisle(Extradimensional_Part1.LAYER_029).aisle(Extradimensional_Part1.LAYER_030)
                .aisle(Extradimensional_Part1.LAYER_031).aisle(Extradimensional_Part1.LAYER_032)
                .aisle(Extradimensional_Part1.LAYER_033);
    }
}
