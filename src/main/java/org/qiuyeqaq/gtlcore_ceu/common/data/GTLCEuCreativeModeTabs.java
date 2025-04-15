package org.qiuyeqaq.gtlcore_ceu.common.data;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration.REGISTRATE;

public class GTLCEuCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> GTL_CORE_CEU = REGISTRATE.defaultCreativeTab(GTLCore_CEu.MOD_ID,
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(GTLCore_CEu.MOD_ID, REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", GTLCore_CEu.id("creative_tab"), "GTL Core CEu"))
                    .icon(GTLCEuItems.MEGA_ULTIMATE_BATTERY::asStack)
                    .build())
            .register();

    public static void init() {}
}
