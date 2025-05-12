package org.qiuyeqaq.gtlcore_ceu.api.machine.feature;

import com.hepdd.gtmthings.api.machine.IWirelessEnergyContainerHolder;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import org.qiuyeqaq.gtlcore_ceu.common.wireless.ExtendWirelessEnergyContainer;

import javax.annotation.Nullable;

public interface IExtendWirelessEnergyContainerHolder extends IWirelessEnergyContainerHolder {

    @Override
    @Nullable
    default ExtendWirelessEnergyContainer getWirelessEnergyContainer() {
        if (getUUID() != null && getWirelessEnergyContainerCache() == null) {
            WirelessEnergyContainer container = WirelessEnergyContainer.getOrCreateContainer(getUUID());
            setWirelessEnergyContainerCache(container);
        }
        return (ExtendWirelessEnergyContainer) getWirelessEnergyContainerCache();
    }
}
