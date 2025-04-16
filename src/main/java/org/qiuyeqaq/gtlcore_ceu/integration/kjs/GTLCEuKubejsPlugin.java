package org.qiuyeqaq.gtlcore_ceu.integration.kjs;

import org.qiuyeqaq.gtlcore_ceu.api.data.chemical.info.GTLCEuMaterialFlags;
import org.qiuyeqaq.gtlcore_ceu.api.data.chemical.info.GTLCEuMaterialIconSet;
import org.qiuyeqaq.gtlcore_ceu.api.item.tool.GTLCEuToolType;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.GTLCEuCleanroomType;
import org.qiuyeqaq.gtlcore_ceu.common.data.*;
import org.qiuyeqaq.gtlcore_ceu.common.recipe.condition.GravityCondition;
import org.qiuyeqaq.gtlcore_ceu.utils.Registries;
import org.qiuyeqaq.gtlcore_ceu.utils.TextUtil;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ClassFilter;

public class GTLCEuKubejsPlugin extends KubeJSPlugin {

    @Override
    public void registerClasses(ScriptType type, ClassFilter filter) {
        super.registerClasses(type, filter);
        filter.allow("org.qiuyeqaq.gtlcore_ceu");
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);

        event.add("GTLCEuMaterials", GTLCEuMaterials.class);
        event.add("GTLCEuBlocks", GTLCEuBlocks.class);
        event.add("GTLCEuItems", GTLCEuItems.class);
        event.add("GTLCEuMachines", GTLCEuMachines.class);
        event.add("GTLCEuRecipeTypes", GTLCEuRecipeTypes.class);
        event.add("GTLCEuRecipeModifiers", GTLCEuRecipeModifiers.class);
        event.add("GTLCEuCleanroomType", GTLCEuCleanroomType.class);
        event.add("GTLCEuToolType", GTLCEuToolType.class);
        event.add("GTLCEuMaterialIconSet", GTLCEuMaterialIconSet.class);
        event.add("GTLCEuMaterialFlags", GTLCEuMaterialFlags.class);
        event.add("TextUtil", TextUtil.class);
        event.add("Registries", Registries.class);
        event.add("GravityCondition", GravityCondition.class);
    }
}
