package org.qiuyeqaq.gtlcore_ceu.mixin.gtm.gui;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.misc.ProspectorMode;
import com.gregtechceu.gtceu.api.gui.texture.ProspectingTexture;
import com.gregtechceu.gtceu.api.gui.widget.ProspectingMapWidget;
import com.llamalad7.mixinextras.sugar.Local;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.SearchComponentWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import org.qiuyeqaq.gtlcore_ceu.client.gui.IProspectingTextureMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProspectingMapWidget.class)
public abstract class ProspectingMapWidgetMixin  extends WidgetGroup implements SearchComponentWidget.IWidgetSearch<Object> {

    @Shadow(remap = false)
    private ProspectingTexture texture;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void intiHooks(int xPosition, int yPosition, int width, int height, int chunkRadius,
                          ProspectorMode<?> mode, int scanTick, CallbackInfo ci,
                          @Local(ordinal = 6) int imageWidth, @Local(ordinal = 7) int imageHeight) {
        // remove
        this.widgets.remove(0);
        this.addWidget(0, new ImageWidget(0, (height - imageHeight) / 2 - 4, imageWidth + 8, imageHeight + 8,
                GuiTextures.BACKGROUND_INVERSE) {

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                if (isMouseOverElement(mouseX, mouseY)) {
                    if (button == 0) {
                        ((IProspectingTextureMixin) texture)
                                .gTLCoreCEu$teleportWayPoint(gui.entityPlayer, mouseX - getPositionX() - 4, mouseY - getPositionY() - 4);
                    } else if (button == 1) {
                        ((IProspectingTextureMixin) texture)
                                .gTLCoreCEu$addWayPoint(gui.entityPlayer, mouseX - getPositionX() - 4, mouseY - getPositionY() - 4);
                    }
                }
                return false;
            }
        });
    }
}
