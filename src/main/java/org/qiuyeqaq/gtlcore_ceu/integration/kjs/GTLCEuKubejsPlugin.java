package org.qiuyeqaq.gtlcore_ceu.integration.kjs;

import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBlocks;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;
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

        event.add("GTLCEuBlocks", GTLCEuBlocks.class);
        event.add("GTLCEuItems", GTLCEuItems.class);
        event.add("TextUtil", TextUtil.class);
        event.add("Registries", Registries.class);
    }
}
