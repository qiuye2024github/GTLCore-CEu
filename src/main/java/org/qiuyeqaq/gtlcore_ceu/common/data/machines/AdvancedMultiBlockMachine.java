package org.qiuyeqaq.gtlcore_ceu.common.data.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.qiuyeqaq.gtlcore_ceu.api.registries.GTLCEuRegistration;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.*;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuBlocks;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuCreativeModeTabs;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMachines;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeTypes;
import org.qiuyeqaq.gtlcore_ceu.client.renderer.machine.*;
import org.qiuyeqaq.gtlcore_ceu.common.data.machines.MultiblockStructure.Multiblock;
import org.qiuyeqaq.gtlcore_ceu.utils.Registries;

@SuppressWarnings("unused")
public class AdvancedMultiBlockMachine {

    public static void init() {}

    public static final MultiblockMachineDefinition EYE_OF_HARMONY;
    public static final MultiblockMachineDefinition DIMENSIONALLY_TRANSCENDENT_DIRT_FORGE;

    static {
        GTLCEuRegistration.REGISTRATE.creativeModeTab(() -> GTLCEuCreativeModeTabs.GTL_CORE_CEU_MACHINES);
    }

    static {
        DIMENSIONALLY_TRANSCENDENT_DIRT_FORGE = GTLCEuRegistration.REGISTRATE.multiblock("dimensionally_transcendent_dirt_forge", NoEnergyMultiblockMachine::new)
                .rotationState(RotationState.ALL)
                .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
                .tooltips(Component.translatable("gtlcore_ceu.machine.dimensionally_transcendent_dirt_forge.tooltip.0"))
                .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                        Component.translatable("gtceu.primitive_blast_furnace")))
                .tooltipBuilder(GTLCEuMachines.GTL_ADD)
                .appearanceBlock(GTBlocks.CASING_PRIMITIVE_BRICKS)
                .pattern(definition -> Multiblock.EXTRADIMENSIONAL
                        .where('~', Predicates.controller(Predicates.blocks(definition.get())))
                        .where(' ', Predicates.any())
                        .where('A', Predicates.blocks(Registries.getBlock("gtceu:firebricks"))
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1)))
                        .where('B', Predicates.blocks(Registries.getBlock("minecraft:bricks")))
                        .where('C', Predicates.blocks(Registries.getBlock("minecraft:dirt")))
                        .where('D', Predicates.blocks(Registries.getBlock("gtceu:firebricks")))
                        .where('E', Predicates.blocks(Registries.getBlock("minecraft:stone_bricks")))
                        .build())
                .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                        GTCEu.id("block/multiblock/primitive_blast_furnace"))
                .register();

        EYE_OF_HARMONY = GTLCEuRegistration.REGISTRATE.multiblock("eye_of_harmony", WorkableElectricMultiblockMachine::new)
                .rotationState(RotationState.NON_Y_AXIS)
                .allowExtendedFacing(false)
                .recipeType(GTLCEuRecipeTypes.COSMOS_SIMULATION_RECIPES)
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.0"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.1"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.2"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.3"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.4"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.5"))
                .tooltips(Component.translatable("gtlcore_ceu.machine.eye_of_harmony.tooltip.6"))
                .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                        Component.translatable("gtlcore_ceu.cosmos_simulation")))
                .tooltipBuilder(GTLCEuMachines.GTL_ADD)
                .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
                .pattern(definition -> Multiblock.EYE_OF_HARMONY
                        .where('~', Predicates.controller(Predicates.blocks(definition.get())))
                        .where(" ", Predicates.any())
                        .where('A', Predicates.blocks(GTBlocks.HIGH_POWER_CASING.get())
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(1))
                                .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1)))
                        .where('B', Predicates.blocks(Registries.getBlock("kubejs:dimensional_bridge_casing")))
                        .where('C', Predicates.blocks(Registries.getBlock("kubejs:dimensional_stability_casing")))
                        .where('D', Predicates.blocks(GTLCEuBlocks.DIMENSIONALLY_TRANSCENDENT_CASING.get()))
                        .where('E', Predicates.blocks(GTLCEuBlocks.DIMENSION_INJECTION_CASING.get()))
                        .where('F', Predicates.blocks(Registries.getBlock("kubejs:spacetime_compression_field_generator")))
                        .build())
                .renderer(EyeOfHarmonyRenderer::new)
                .hasTESR(true)
                .register();
    }
}
