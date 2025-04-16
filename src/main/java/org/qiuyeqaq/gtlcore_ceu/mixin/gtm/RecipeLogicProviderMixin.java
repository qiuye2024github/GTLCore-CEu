package org.qiuyeqaq.gtlcore_ceu.mixin.gtm;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gregtechceu.gtceu.integration.jade.provider.RecipeLogicProvider;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

@Mixin(RecipeLogicProvider.class)
public abstract class RecipeLogicProviderMixin extends CapabilityBlockProvider<RecipeLogic> {

    @Unique
    private static final ChatFormatting[] GTL_CORE$VC = {
            ChatFormatting.DARK_GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.AQUA,
            ChatFormatting.GOLD,
            ChatFormatting.DARK_PURPLE,
            ChatFormatting.BLUE,
            ChatFormatting.LIGHT_PURPLE,
            ChatFormatting.RED,
            ChatFormatting.DARK_AQUA,
            ChatFormatting.DARK_RED,
            ChatFormatting.GREEN,
            ChatFormatting.DARK_GREEN,
            ChatFormatting.YELLOW,
            ChatFormatting.BLUE,
            ChatFormatting.RED
    };

    protected RecipeLogicProviderMixin(ResourceLocation uid) {
        super(uid);
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block,
                              BlockEntity blockEntity, IPluginConfig config) {
        if (capData.getBoolean("Working")) {
            var recipeInfo = capData.getCompound("Recipe");
            if (!recipeInfo.isEmpty()) {
                var EUt = recipeInfo.getLong("EUt");
                var isInput = recipeInfo.getBoolean("isInput");

                long absEUt = Math.abs(EUt);

                // Default behavior, if this TE is not a steam machine (or somehow not instanceof
                // IGregTechTileEntity...)
                var tier = GTUtil.getTierByVoltage(absEUt);
                Component text = Component.literal(FormattingUtil.formatNumbers(absEUt)).withStyle(ChatFormatting.RED)
                        .append(Component.literal(" EU/t").withStyle(ChatFormatting.RESET)
                                .append(Component.literal(" (").withStyle(ChatFormatting.GREEN)
                                        .append(Component
                                                .translatable("gtceu.top.electricity",
                                                        FormattingUtil.formatNumber2Places(absEUt / ((float) GTValues.V[tier])),
                                                        GTValues.VNF[tier])
                                                .withStyle(style -> style.withColor(GTL_CORE$VC[tier])))
                                        .append(Component.literal(")").withStyle(ChatFormatting.GREEN))));

                if (EUt > 0) {
                    if (isInput) {
                        tooltip.add(Component.translatable("gtceu.top.energy_consumption").append(" ").append(text));
                    } else {
                        tooltip.add(Component.translatable("gtceu.top.energy_production").append(" ").append(text));
                    }
                }
            }
        }
    }
}
