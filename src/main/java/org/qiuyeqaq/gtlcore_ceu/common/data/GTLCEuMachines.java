package org.qiuyeqaq.gtlcore_ceu.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class GTLCEuMachines {

    public static void init() {}

    static {
        REGISTRATE.creativeModeTab(() -> GTCreativeModeTabs.MACHINE);
    }
}
