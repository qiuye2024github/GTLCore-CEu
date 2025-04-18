package org.qiuyeqaq.gtlcore_ceu.api.data.chemical.info;

import org.qiuyeqaq.gtlcore_ceu.client.renderer.item.StereoscopicItemRenderer;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.item.component.ICustomRenderer;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class GTLCEuMaterialIconSet extends com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet {

    private final ICustomRenderer customRenderer;

    public GTLCEuMaterialIconSet(@NotNull String name,
                                 @Nullable com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet parentIconset,
                                 boolean isRootIconset,
                                 ICustomRenderer customRenderer) {
        super(name, parentIconset, isRootIconset);
        this.customRenderer = customRenderer;
    }

    public static final GTLCEuMaterialIconSet CUSTOM_TRANSCENDENT_MENTAL = new GTLCEuMaterialIconSet(
            "transcendent_mental",
            MaterialIconSet.METALLIC,
            false,
            () -> StereoscopicItemRenderer.INSTANCE);

    public static final MaterialIconSet LIMPID = new MaterialIconSet("limpid", DULL);
}
