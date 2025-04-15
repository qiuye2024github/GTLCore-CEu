package org.qiuyeqaq.gtlcore_ceu.integration.jei;

import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

import net.minecraft.resources.ResourceLocation;

import com.lowdragmc.lowdraglib.LDLib;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class GTJEIPlugin implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return GTLCore_CEu.id("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        if (LDLib.isReiLoaded() || LDLib.isEmiLoaded()) return;
    }
}
