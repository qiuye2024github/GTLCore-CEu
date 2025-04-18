package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.VN;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipes.DISASSEMBLY_RECORD;

public class RemoveRecipe {

    public static void init(Consumer<ResourceLocation> consumer) {
        DISASSEMBLY_RECORD.clear();
        consumer.accept(GTCEu.id("macerator/macerate_wheat"));
        consumer.accept(GTCEu.id("autoclave/agar"));
        consumer.accept(GTCEu.id("assembly_line/dynamo_hatch_uhv"));
        consumer.accept(GTCEu.id("assembly_line/energy_hatch_uhv"));
        consumer.accept(GTCEu.id("research_station/1_x_gtceu_uv_energy_input_hatch"));
        consumer.accept(GTCEu.id("research_station/1_x_gtceu_uv_energy_output_hatch"));
        consumer.accept(GTCEu.id("mixer/ender_pearl_dust"));

        Material[] fluidMap = new Material[] { GTMaterials.Glue, GTMaterials.Palladium,
                GTMaterials.Polytetrafluoroethylene, GTMaterials.Polybenzimidazole };

        for (var machine : GTMachines.FLUID_IMPORT_HATCH) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "fluid_hatch_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }

        for (var machine : GTMachines.FLUID_EXPORT_HATCH) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "fluid_export_hatch_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }

        for (var machine : GTMachines.ITEM_IMPORT_BUS) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "item_import_bus_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }

        for (var machine : GTMachines.ITEM_EXPORT_BUS) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "item_export_bus_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }

        for (var machine : GTMachines.DUAL_IMPORT_HATCH) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "dual_import_bus_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }

        for (var machine : GTMachines.DUAL_EXPORT_HATCH) {
            if (machine == null) continue;
            int tier = machine.getTier();
            int j = Math.min(fluidMap.length - 1, tier / 2);
            for (; j < fluidMap.length; j++) {
                consumer.accept(GTCEu.id("assembler/" + "dual_export_bus_" + VN[tier].toLowerCase() + "_" + fluidMap[j].getName()));
            }
        }
    }
}
