package org.qiuyeqaq.gtlcore_ceu;

import org.qiuyeqaq.gtlcore_ceu.utils.StorageManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import org.qiuyeqaq.gtlcore_ceu.client.ClientProxy;
import com.mojang.logging.LogUtils;
import org.qiuyeqaq.gtlcore_ceu.common.CommonProxy;
import org.slf4j.Logger;

import java.util.Objects;

@Mod(GTLCore_CEu.MOD_ID)
public class GTLCore_CEu {

    public static final String MOD_ID = "gtlcore_ceu";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String VERSION = "0.0.1";
    public static StorageManager STORAGE_INSTANCE = new StorageManager();

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public GTLCore_CEu() {
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(GTLCore_CEu::worldTick);
    }

    public static void worldTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side.isServer()) {
            STORAGE_INSTANCE = StorageManager.getInstance(Objects.requireNonNull(event.level.getServer()));
        }
    }
}
