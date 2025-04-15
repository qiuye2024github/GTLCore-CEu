package org.qiuyeqaq.gtlcore_ceu.integration.kjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ClassFilter;
import org.qiuyeqaq.gtlcore_ceu.utils.Registries;
import org.qiuyeqaq.gtlcore_ceu.utils.TextUtil;

public class GTLCEuKubejsPlugin extends KubeJSPlugin {

    @Override
    public void registerClasses(ScriptType type, ClassFilter filter) {
        super.registerClasses(type, filter);
        filter.allow("org.qiuyeqaq.gtlcore_ceu");
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);

        event.add("TextUtil", TextUtil.class);
        event.add("Registries", Registries.class);
    }
}
