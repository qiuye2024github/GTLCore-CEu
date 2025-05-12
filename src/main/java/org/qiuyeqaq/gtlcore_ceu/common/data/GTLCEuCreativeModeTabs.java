package org.qiuyeqaq.gtlcore_ceu.common.data;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;
import org.qiuyeqaq.gtlcore_ceu.common.data.machines.AdvancedMultiBlockMachine;

import static org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration.REGISTRATE;

public class GTLCEuCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> GTL_CORE_CEU;
    public static RegistryEntry<CreativeModeTab> GTL_CORE_CEU_MACHINES;

    static {
        GTL_CORE_CEU = REGISTRATE.defaultCreativeTab(GTLCore_CEu.MOD_ID,
                        builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(GTLCore_CEu.MOD_ID, REGISTRATE))
                                .title(REGISTRATE.addLang("itemGroup", GTLCore_CEu.id("creative_tab"), "GTL Core CEu"))
                                .icon(GTLCEuItems.MEGA_ULTIMATE_BATTERY::asStack)
                                .build())
                .register();

        GTL_CORE_CEU_MACHINES = REGISTRATE.defaultCreativeTab(GTLCore_CEu.MOD_ID,
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(GTLCore_CEu.MOD_ID, REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", GTLCore_CEu.id("machines_item"), "GTL Core CEu Machines Items"))
                            .icon(AdvancedMultiBlockMachine.EYE_OF_HARMONY::asStack)
                            .build())
            .register();
    }

    public static void init() {}
}
