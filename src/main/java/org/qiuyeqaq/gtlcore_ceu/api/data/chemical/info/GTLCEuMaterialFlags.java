package org.qiuyeqaq.gtlcore_ceu.api.data.chemical.info;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class GTLCEuMaterialFlags {

    public static final MaterialFlag GENERATE_NANOSWARM = new MaterialFlag.Builder("generate_nanoswarm")
            .build();

    public static final MaterialFlag GENERATE_MILLED = new MaterialFlag.Builder("generate_milled")
            .build();
}
