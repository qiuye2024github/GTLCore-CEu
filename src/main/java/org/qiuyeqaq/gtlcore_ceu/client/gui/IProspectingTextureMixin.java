package org.qiuyeqaq.gtlcore_ceu.client.gui;

import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Unique;

public interface IProspectingTextureMixin {

    void gTLCoreCEu$addWayPoint(Player player, double mouseX, double mouseY);

    @Unique
    void gTLCoreCEu$teleportWayPoint(Player player, double mouseX, double mouseY);
}
