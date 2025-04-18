package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuItems;
import org.qiuyeqaq.gtlcore_ceu.common.recipe.condition.GravityCondition;

import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMaterials.WaterAgarMix;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeTypes.*;

public class Misc {

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapelessRecipe(provider, "structure_detect",
                GTLCEuItems.STRUCTURE_DETECT.asStack(),
                "A", Items.PAPER);

        // VanillaRecipeHelper.addShapelessRecipe(provider, "simulation_machine",
        // AdvancedMultiBlockMachine.SIMULATION_MACHINE.asStack(), "A", Blocks.STONE);

        WOOD_DISTILLATION_RECIPES.recipeBuilder("wood_distillation_recipes")
                .inputItems(ItemTags.LOGS, 16)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(dust, DarkAsh, 8)
                .outputFluids(Water.getFluid(800))
                .outputFluids(Carbon.getFluid(490))
                .outputFluids(Methanol.getFluid(480))
                .outputFluids(Benzene.getFluid(350))
                .outputFluids(CarbonMonoxide.getFluid(340))
                .outputFluids(Creosote.getFluid(300))
                .outputFluids(Dimethylbenzene.getFluid(240))
                .outputFluids(AceticAcid.getFluid(160))
                .outputFluids(Methane.getFluid(130))
                .outputFluids(Acetone.getFluid(80))
                .outputFluids(Phenol.getFluid(75))
                .outputFluids(Toluene.getFluid(75))
                .outputFluids(Ethylene.getFluid(20))
                .outputFluids(Hydrogen.getFluid(20))
                .outputFluids(MethylAcetate.getFluid(16))
                .outputFluids(Ethanol.getFluid(16))
                .duration(200).EUt(VA[MV])
                .save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder("water_agar_mix").EUt(VA[HV]).duration(600)
                .inputItems(dust, Gelatin)
                .inputFluids(DistilledWater.getFluid(1000))
                .outputFluids(WaterAgarMix.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder("agar")
                .inputFluids(WaterAgarMix.getFluid(1000))
                .outputItems(dust, Agar, 1)
                .duration(420).EUt(VA[MV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder("soda_ash_from_carbon_dioxide")
                .circuitMeta(2)
                .inputItems(dust, SodiumHydroxide, 6)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodaAsh, 6)
                .outputFluids(Water.getFluid(1000))
                .duration(80).EUt(VA[HV])
                .save(provider);

        BLAST_RECIPES.recipeBuilder("engraved_crystal_chip_from_emerald")
                .inputItems(plate, Emerald)
                .inputItems(RAW_CRYSTAL_CHIP)
                .inputFluids(Helium.getFluid(1000))
                .outputItems(ENGRAVED_CRYSTAL_CHIP)
                .blastFurnaceTemp(5000)
                .duration(900).EUt(VA[HV])
                .addCondition(new GravityCondition(true))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("engraved_crystal_chip_from_olivine")
                .inputItems(plate, Olivine)
                .inputItems(RAW_CRYSTAL_CHIP)
                .inputFluids(Helium.getFluid(1000))
                .outputItems(ENGRAVED_CRYSTAL_CHIP)
                .blastFurnaceTemp(5000)
                .duration(900).EUt(VA[HV])
                .addCondition(new GravityCondition(true))
                .save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder("quantum_star")
                .inputItems(gem, NetherStar)
                .inputFluids(Radon.getFluid(1250))
                .outputItems(QUANTUM_STAR)
                .duration(1920).EUt(VA[HV])
                .addCondition(new GravityCondition(true))
                .save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder("gravi_star")
                .inputItems(QUANTUM_STAR)
                .inputFluids(Neutronium.getFluid(L * 2))
                .outputItems(GRAVI_STAR)
                .duration(480).EUt(VA[IV])
                .addCondition(new GravityCondition(true))
                .save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder("quantum_eye")
                .inputItems(gem, EnderEye)
                .inputFluids(Radon.getFluid(250))
                .outputItems(QUANTUM_EYE)
                .duration(480).EUt(VA[HV])
                .addCondition(new GravityCondition(true))
                .save(provider);

        LIGHTNING_PROCESSOR_RECIPES.recipeBuilder("ender_pearl_dust").duration(400).EUt(VA[LV])
                .inputItems(dust, Beryllium)
                .inputItems(dust, Potassium, 4)
                .inputFluids(Nitrogen.getFluid(5000))
                .circuitMeta(1)
                .outputItems(dust, EnderPearl, 10)
                .save(provider);
    }
}
