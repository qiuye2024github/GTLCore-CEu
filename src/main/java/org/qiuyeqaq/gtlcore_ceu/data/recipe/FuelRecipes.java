package org.qiuyeqaq.gtlcore_ceu.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.data.recipes.FinishedRecipe;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMaterials;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuRecipeTypes.*;

public class FuelRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        COMBUSTION_GENERATOR_FUELS.recipeBuilder("naphtha")
                .inputFluids(Naphtha.getFluid(1))
                .duration(20)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("sulfuric_light_fuel")
                .inputFluids(SulfuricLightFuel.getFluid(4))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("methanol")
                .inputFluids(Methanol.getFluid(4))
                .duration(16)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("ethanol")
                .inputFluids(Ethanol.getFluid(1))
                .duration(12)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("octane")
                .inputFluids(Octane.getFluid(2))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("biodiesel")
                .inputFluids(BioDiesel.getFluid(1))
                .duration(16)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("light_fuel")
                .inputFluids(LightFuel.getFluid(1))
                .duration(20)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("diesel")
                .inputFluids(Diesel.getFluid(1))
                .duration(30)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("cetane_diesel")
                .inputFluids(CetaneBoostedDiesel.getFluid(2))
                .duration(90)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("rocket_fuel")
                .inputFluids(RocketFuel.getFluid(16))
                .duration(250)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("gasoline")
                .inputFluids(Gasoline.getFluid(1))
                .duration(100)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("high_octane_gasoline")
                .inputFluids(HighOctaneGasoline.getFluid(1))
                .duration(200)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("toluene")
                .inputFluids(Toluene.getFluid(1))
                .duration(20)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("light_oil")
                .inputFluids(OilLight.getFluid(32))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("raw_oil")
                .inputFluids(RawOil.getFluid(64))
                .duration(30)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("natural_gas")
                .inputFluids(NaturalGas.getFluid(8))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("wood_gas")
                .inputFluids(WoodGas.getFluid(8))
                .duration(12)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("sulfuric_gas")
                .inputFluids(SulfuricGas.getFluid(32))
                .duration(50)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("sulfuric_naphtha")
                .inputFluids(SulfuricNaphtha.getFluid(4))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("coal_gas")
                .inputFluids(CoalGas.getFluid(1))
                .duration(6)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("methane")
                .inputFluids(Methane.getFluid(2))
                .duration(14)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("ethylene")
                .inputFluids(Ethylene.getFluid(1))
                .duration(8)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("refinery_gas")
                .inputFluids(RefineryGas.getFluid(1))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("ethane")
                .inputFluids(Ethane.getFluid(4))
                .duration(42)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("propene")
                .inputFluids(Propene.getFluid(1))
                .duration(12)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("butadiene")
                .inputFluids(Butadiene.getFluid(16))
                .duration(204)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("propane")
                .inputFluids(Propane.getFluid(4))
                .duration(58)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("butene")
                .inputFluids(Butene.getFluid(1))
                .duration(16)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("phenol")
                .inputFluids(Phenol.getFluid(1))
                .duration(18)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("benzene")
                .inputFluids(Benzene.getFluid(1))
                .duration(22)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("butane")
                .inputFluids(Butane.getFluid(4))
                .duration(74)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("lpg")
                .inputFluids(LPG.getFluid(1))
                .duration(20)
                .EUt(-V[LV])
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder("nitrobenzene")
                .inputFluids(Nitrobenzene.getFluid(1))
                .duration(80)
                .EUt(-V[LV])
                .save(provider);

        SUPERCRITICAL_STEAM_TURBINE_FUELS.recipeBuilder("supercritical_steam")
                .inputFluids(GTLCEuMaterials.SupercriticalSteam.getFluid(1280))
                .outputFluids(DistilledWater.getFluid(8))
                .duration(30)
                .EUt(-V[MV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("seed_oil")
                .inputFluids(SeedOil.getFluid(32))
                .duration(8)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("fish_oil")
                .inputFluids(FishOil.getFluid(32))
                .duration(8)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("biomass")
                .inputFluids(Biomass.getFluid(8))
                .duration(8)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("oil")
                .inputFluids(Oil.getFluid(4))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("oil_light")
                .inputFluids(OilLight.getFluid(4))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("creosote")
                .inputFluids(Creosote.getFluid(4))
                .duration(12)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("oil_heavy")
                .inputFluids(OilHeavy.getFluid(4))
                .duration(15)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("oil_medium")
                .inputFluids(RawOil.getFluid(4))
                .duration(15)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("coal_tar")
                .inputFluids(CoalTar.getFluid(8))
                .duration(8)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("sulfuric_heavy_fuel")
                .inputFluids(SulfuricHeavyFuel.getFluid(2))
                .duration(10)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("glycerol")
                .inputFluids(Glycerol.getFluid(2))
                .duration(41)
                .EUt(-V[LV])
                .save(provider);

        SEMI_FLUID_GENERATOR_FUELS.recipeBuilder("heavy_fuel")
                .inputFluids(HeavyFuel.getFluid(2))
                .duration(45)
                .EUt(-V[LV])
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_1")
                .inputFluids(RocketFuel.getFluid(10))
                .duration(5)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_2")
                .inputFluids(GTLCEuMaterials.RocketFuelRp1.getFluid(10))
                .duration(8)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_3")
                .inputFluids(GTLCEuMaterials.DenseHydrazineFuelMixture.getFluid(10))
                .duration(14)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_4")
                .inputFluids(GTLCEuMaterials.RocketFuelCn3h7o3.getFluid(10))
                .duration(20)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_5")
                .inputFluids(GTLCEuMaterials.RocketFuelH8n4c2o4.getFluid(10))
                .duration(30)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_6")
                .inputFluids(GTLCEuMaterials.ExplosiveHydrazine.getFluid(10))
                .duration(60)
                .EUt(-2048)
                .save(provider);

        ROCKET_ENGINE_FUELS.recipeBuilder("rocket_engine_fuel_8")
                .inputFluids(GTLCEuMaterials.StellarEnergyRocketFuel.getFluid(10))
                .duration(240)
                .EUt(-2048)
                .save(provider);

        NAQUADAH_REACTOR.recipeBuilder("naquadah")
                .inputFluids(Naquadah.getFluid(1))
                .duration(100)
                .EUt(-32768)
                .save(provider);

        NAQUADAH_REACTOR.recipeBuilder("enriched_naquadah")
                .inputFluids(NaquadahEnriched.getFluid(1))
                .duration(150)
                .EUt(-32768)
                .save(provider);

        NAQUADAH_REACTOR.recipeBuilder("naquadah_fuel")
                .inputFluids(GTLCEuMaterials.NaquadahFuel.getFluid(1))
                .duration(875)
                .EUt(-32768)
                .save(provider);

        NAQUADAH_REACTOR.recipeBuilder("enriched_naquadah_fuel")
                .inputFluids(GTLCEuMaterials.EnrichedNaquadahFuel.getFluid(1))
                .duration(1250)
                .EUt(-32768)
                .save(provider);

        LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_fuel")
                .inputFluids(GTLCEuMaterials.NaquadahFuel.getFluid(1))
                .inputFluids(Hydrogen.getFluid(10))
                .duration(875)
                .EUt(-131072)
                .save(provider);

        LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("enriched_naquadah_fuel")
                .inputFluids(GTLCEuMaterials.EnrichedNaquadahFuel.getFluid(1))
                .inputFluids(Hydrogen.getFluid(10))
                .duration(1250)
                .EUt(-131072)
                .save(provider);

        LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_fuel1")
                .inputFluids(GTLCEuMaterials.NaquadahFuel.getFluid(10))
                .inputFluids(Oxygen.getFluid(FluidStorageKeys.PLASMA, 10))
                .duration(16 * 875)
                .EUt(-131072)
                .save(provider);

        LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("enriched_naquadah_fuel1")
                .inputFluids(GTLCEuMaterials.EnrichedNaquadahFuel.getFluid(10))
                .inputFluids(Nitrogen.getFluid(FluidStorageKeys.PLASMA, 10))
                .duration(16 * 1250)
                .EUt(-131072)
                .save(provider);

        HYPER_REACTOR_RECIPES.recipeBuilder("hyper_fuel_1")
                .inputFluids(GTLCEuMaterials.HyperFuel1.getFluid(1))
                .inputFluids(Argon.getFluid(FluidStorageKeys.PLASMA, 1))
                .duration(200)
                .EUt(-GTValues.V[GTValues.UEV])
                .save(provider);

        HYPER_REACTOR_RECIPES.recipeBuilder("hyper_fuel_2")
                .inputFluids(GTLCEuMaterials.HyperFuel2.getFluid(1))
                .inputFluids(Iron.getFluid(FluidStorageKeys.PLASMA, 1))
                .duration(200)
                .EUt(-GTValues.V[GTValues.UIV])
                .save(provider);

        HYPER_REACTOR_RECIPES.recipeBuilder("hyper_fuel_3")
                .inputFluids(GTLCEuMaterials.HyperFuel3.getFluid(1))
                .inputFluids(Nickel.getFluid(FluidStorageKeys.PLASMA, 1))
                .duration(200)
                .EUt(-GTValues.V[GTValues.UXV])
                .save(provider);

        HYPER_REACTOR_RECIPES.recipeBuilder("hyper_fuel_4")
                .inputFluids(GTLCEuMaterials.HyperFuel4.getFluid(1))
                .inputFluids(GTLCEuMaterials.DegenerateRhenium.getFluid(FluidStorageKeys.PLASMA, 1))
                .duration(200)
                .EUt(-GTValues.V[GTValues.OpV])
                .save(provider);

        ADVANCED_HYPER_REACTOR_RECIPES.recipeBuilder("concentration_mixing_hyper_fuel_1")
                .inputFluids(GTLCEuMaterials.ConcentrationMixingHyperFuel1.getFluid(1))
                .duration(200)
                .EUt(-4 * GTValues.V[GTValues.MAX])
                .save(provider);

        ADVANCED_HYPER_REACTOR_RECIPES.recipeBuilder("concentration_mixing_hyper_fuel_2")
                .inputFluids(GTLCEuMaterials.ConcentrationMixingHyperFuel2.getFluid(1))
                .duration(200)
                .EUt(-16 * GTValues.V[GTValues.MAX])
                .save(provider);
    }
}
