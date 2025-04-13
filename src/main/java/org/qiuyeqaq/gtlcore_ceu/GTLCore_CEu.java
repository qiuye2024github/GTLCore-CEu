package org.qiuyeqaq.gtlcore_ceu;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(GTLCore_CEu.MOD_ID)
public class GTLCore_CEu {

    public static final String MOD_ID = "gtlcore_ceu";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String VERSION = "0.0.1";
    public static StorageManager STORAGE_INSTANCE = new StorageManager();

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


}
