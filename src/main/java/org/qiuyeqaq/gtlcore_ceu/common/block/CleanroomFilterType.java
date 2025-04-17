package org.qiuyeqaq.gtlcore_ceu.common.block;

import com.gregtechceu.gtceu.api.block.IFilterType;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.GTLCEuCleanroomType;

public enum CleanroomFilterType implements IFilterType {

    FILTER_CASING_LAW("law_filter_casing", GTLCEuCleanroomType.LAW_CLEANROOM);

    private final String name;
    @Getter
    private final CleanroomType cleanroomType;

    CleanroomFilterType(String name, CleanroomType cleanroomType) {
        this.name = name;
        this.cleanroomType = cleanroomType;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return this.name;
    }

    @NotNull
    @Override
    public String toString() {
        return getSerializedName();
    }
}
