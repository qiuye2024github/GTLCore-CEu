package org.qiuyeqaq.gtlcore_ceu.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.qiuyeqaq.gtlcore_ceu.common.CommonProxy;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();
        init();
    }
}
