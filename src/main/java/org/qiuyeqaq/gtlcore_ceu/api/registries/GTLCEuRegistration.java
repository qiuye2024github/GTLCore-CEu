package org.qiuyeqaq.gtlcore_ceu.api.registries;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class GTLCEuRegistration {

    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GTLCore_CEu.MOD_ID);

    static {
        GTLCEuRegistration.REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    }

    private GTLCEuRegistration() {/**/}
}
