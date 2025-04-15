package org.qiuyeqaq.gtlcore_ceu.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidDefinition;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GTLCEuBedrockFluids {

    public static final Map<ResourceLocation, BedrockFluidDefinition> toReRegister = new HashMap<>();

    public static void init() {
        toReRegister.forEach(GTRegistries.BEDROCK_FLUID_DEFINITIONS::registerOrOverride);
    }

    public static BedrockFluidDefinition create(ResourceLocation id,
                                                Consumer<BedrockFluidDefinition.Builder> consumer) {
        BedrockFluidDefinition.Builder builder = BedrockFluidDefinition.builder(id);
        consumer.accept(builder);

        BedrockFluidDefinition definition = builder.build();
        toReRegister.put(id, definition);
        return definition;
    }

    public static Set<ResourceKey<Level>> getDim(String namespace, String path) {
        return Set.of(ResourceKey.create(Registries.DIMENSION,
                new ResourceLocation(namespace, path)));
    }
}
