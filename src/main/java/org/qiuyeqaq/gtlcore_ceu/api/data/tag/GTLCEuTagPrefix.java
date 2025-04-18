package org.qiuyeqaq.gtlcore_ceu.api.data.tag;

import org.qiuyeqaq.gtlcore_ceu.api.data.chemical.info.GTLCEuMaterialFlags;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

@SuppressWarnings("unused")
public class GTLCEuTagPrefix {

    public static void init() {}

    public static final TagPrefix nanoswarm = new TagPrefix("nanoswarm")
            .idPattern("%s_nanoswarm")
            .defaultTagPath("nanoswarms/%s")
            .unformattedTagPath("nanoswarms")
            .langValue(" %s nanoswarm")
            .materialAmount(GTValues.M)
            .materialIconType(new MaterialIconType("nanoswarm"))
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasFlag(GTLCEuMaterialFlags.GENERATE_NANOSWARM));

    public static final TagPrefix milled = new TagPrefix("milled")
            .idPattern("milled_%s")
            .defaultTagPath("milled/%s")
            .unformattedTagPath("milled")
            .langValue("Milled %s")
            .materialAmount(GTValues.M)
            .materialIconType(new MaterialIconType("milled"))
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasFlag(GTLCEuMaterialFlags.GENERATE_MILLED));
}
