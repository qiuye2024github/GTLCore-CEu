package org.qiuyeqaq.gtlcore_ceu.common.recipe.condition;

import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeConditions;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class GravityCondition extends RecipeCondition {

    public static final Codec<GravityCondition> CODEC = RecordCodecBuilder
            .create(instance -> RecipeCondition.isReverse(instance)
                    .and(Codec.BOOL.fieldOf("gravity").forGetter(val -> val.zero))
                    .apply(instance, GravityCondition::new));

    public final static GravityCondition INSTANCE = new GravityCondition();

    private boolean zero = false;

    public GravityCondition(boolean zero) {
        this.zero = zero;
    }

    public GravityCondition(boolean isReverse, boolean zero) {
        super(isReverse);
        this.zero = zero;
    }

    @Override
    public RecipeConditionType<?> getType() {
        return GTLCEuRecipeConditions.GRAVITY;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable("gtlcore.condition." + (zero ? "zero_" : "") + "gravity");
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new GravityCondition();
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        JsonObject config = super.serialize();
        config.addProperty("gravity", zero);
        return config;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        zero = GsonHelper.getAsBoolean(config, "gravity", false);
        return this;
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        zero = buf.readBoolean();
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeBoolean(zero);
    }
}
