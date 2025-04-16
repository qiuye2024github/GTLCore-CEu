package org.qiuyeqaq.gtlcore_ceu.client.gui;

import appeng.client.gui.style.Blitter;
import net.minecraft.resources.ResourceLocation;
import org.qiuyeqaq.gtlcore_ceu.GTLCore_CEu;

public enum ModifyIcon {

    MULTIPLY_2(0, 0),
    MULTIPLY_3(16, 0),
    MULTIPLY_5(32, 0),
    DIVISION_2(0, 16),
    DIVISION_3(16, 16),
    DIVISION_5(32, 16),
    TOOLBAR_BUTTON_BACKGROUND(32, 32);

    public final int x;
    public final int y;
    public final int width;
    public final int height;
    public static final ResourceLocation TEXTURE = GTLCore_CEu.id("textures/guis/states.png");
    public static final int TEXTURE_WIDTH = 48;
    public static final int TEXTURE_HEIGHT = 48;

    ModifyIcon(int x, int y) {
        this(x, y, 16, 16);
    }

    ModifyIcon(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Blitter getBlitter() {
        return Blitter.texture(TEXTURE, TEXTURE_WIDTH, TEXTURE_HEIGHT).src(this.x, this.y, this.width, this.height);
    }
}
