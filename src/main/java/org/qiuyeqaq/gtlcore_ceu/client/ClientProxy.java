package org.qiuyeqaq.gtlcore_ceu.client;

import org.qiuyeqaq.gtlcore_ceu.common.CommonProxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();
        init();
    }

    public static void init() {
        CraftingUnitModelProvider.initCraftingUnitModels();
    }
}
