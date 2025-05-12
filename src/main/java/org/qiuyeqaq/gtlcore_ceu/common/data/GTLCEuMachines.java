package org.qiuyeqaq.gtlcore_ceu.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.client.renderer.machine.MaintenanceHatchPartRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.OverlayTieredMachineRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.hepdd.gtmthings.common.registry.GTMTRegistration;
import com.hepdd.gtmthings.data.CreativeModeTabs;
import com.hepdd.gtmthings.data.WirelessMachines;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import org.qiuyeqaq.gtlcore_ceu.api.machine.multiblock.GTLCEuPartAbility;
import org.qiuyeqaq.gtlcore_ceu.common.data.machines.*;
import org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.generator.*;
import org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part.*;
import org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.part.maintenance.*;

import java.util.List;
import java.util.function.BiConsumer;

import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class GTLCEuMachines {


    public static final BiConsumer<ItemStack, List<Component>> GTL_MODIFY = (stack, components) -> components
            .add(Component.translatable("gtlcore_ceu.registry.modify")
                    .withStyle(style -> style.withColor(TooltipHelper.RAINBOW.getCurrent())));

    public static final BiConsumer<ItemStack, List<Component>> GTL_ADD = (stack, components) -> components
            .add(Component.translatable("gtlcore_ceu.registry.add")
                    .withStyle(style -> style.withColor(TooltipHelper.RAINBOW_SLOW.getCurrent())));

    public static final BiConsumer<IMultiController, List<Component>> CHEMICAL_PLANT_DISPLAY = (controller, components) -> {
        if (controller.isFormed()) {
            double value = 1 - ((CoilWorkableElectricMultiblockMachine) controller).getCoilTier() * 0.05;
            components.add(Component.translatable("gtceu.machine.eut_multiplier.tooltip", value * 0.8));
            components.add(Component.translatable("gtceu.machine.duration_multiplier.tooltip", value * 0.6));
        }
    };

    public static final BiConsumer<IMultiController, List<Component>> MAX_TEMPERATURE = (controller, components) -> {
        if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
            components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                    Component.translatable(FormattingUtil.formatNumbers(coilMachine.getCoilType().getCoilTemperature() + 100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) + "K")
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
        }
    };

    public static final BiConsumer<IMultiController, List<Component>> TEMPERATURE = (controller, components) -> {
        if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
            components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                    Component.translatable(FormattingUtil.formatNumbers(coilMachine.getCoilType().getCoilTemperature()) + "K")
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
        }
    };

    public static final BiConsumer<IMultiController, List<Component>> COIL_PARALLEL = (controller, components) -> {
        if (controller instanceof CoilWorkableElectricMultiblockMachine machine && controller.isFormed()) {
            components.add(Component.translatable("gtceu.multiblock.parallel", Component.literal(FormattingUtil.formatNumbers(Math.min(2147483647, (int) Math.pow(2, ((double) machine.getCoilType().getCoilTemperature() / 900))))).withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GRAY));
        }
    };

    public static final BiConsumer<IMultiController, List<Component>> PROCESSING_PLANT_PARALLEL = (controller, components) -> {
        if (controller.isFormed() && controller instanceof WorkableElectricMultiblockMachine workableElectricMultiblockMachine) {
            components.add(Component.translatable("gtceu.multiblock.parallel", Component.literal(FormattingUtil.formatNumbers(4 * (workableElectricMultiblockMachine.getTier() - 1))).withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GRAY));
        }
    };

    public static void init() {
        TootipsModify.init();
        GeneratorMachine.init();
        AdvancedMultiBlockMachine.init();
        AdditionalMultiBlockMachine.init();
    }

    static {
        REGISTRATE.creativeModeTab(() -> GTCreativeModeTabs.MACHINE);
    }

    public static final FactoryBlockPattern DTPF = FactoryBlockPattern.start()
            .aisle(" ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd   d     d   ddd   ddd ", "         d   d     d   d         ", "         d   d     d   d         ", "                                 ", "                                 ", "                                 ", "         d   d     d   d         ", "         d   d     d   d         ", "         d   d     d   d         ", "                                 ")
            .aisle("dbbbd dbbbd    d d    dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", "dbbbd dbbbd           dbbbd dbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbd dbbbd           dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC   d     d   CCC   CCC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         d   d     d   d         ", "                                 ", "         d   d     d   d         ", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbd dbbbdddddsdsdddddbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "  s     s               s     s  ", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC   d     d   CbC   CbC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "  d     sbbbbbddsddbbbbbs     d  ", "  d      bCCCb     bCCCb      d  ", "  d      d   d     d   d      d  ", "   s                         s   ", "   s     d   d     d   d     s   ", "    ss   bCCCb     bCCCb   ss    ", "      dddbbbbbddsddbbbbbddd      ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbdddbbbd    ded    dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", "dbbbdddbbbd           dbbbdddbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbdddbbbd           dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         dCCCd     dCCCd         ", "  s      dCCCd     dCCCd      s  ", "  s      dCCCd     dCCCd      s  ", "         bCCCb     bCCCb         ", "    ss   bCCCb     bCCCb   ss    ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   dd    d     d    dd   ddd ", "         d   d     d   d         ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "  s      dCCCd     dCCCd      s  ", "   s     d   d     d   d     s   ", "         d   d     d   d         ", "                                 ")
            .aisle("   d   d       ded       d   d   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   d   d                 d   d   ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "   d   d                 d   d   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   d   d                 d   d   ", "                                 ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "  s      dCCCd     dCCCd      s  ", "   s                         s   ", "                                 ", "                                 ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   dd    d     d    dd   ddd ", "         d   d     d   d         ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "         dCCCd     dCCCd         ", "  d      d   d     d   d      d  ", "         d   d     d   d         ", "                                 ")
            .aisle("dbbbdddbbbd    ded    dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", "dbbbdddbbbd           dbbbdddbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbdddbbbd           dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         dCCCd     dCCCd         ", "         dCCCd     dCCCd         ", "         dCCCd     dCCCd         ", "         bCCCb     bCCCb         ", "  d      bCCCb     bCCCb      d  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbd dbbbdddddsdsdddddbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "  s     s               s     s  ", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC   d     d   CbC   CbC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "  s     sbbbbbddsddbbbbbs     s  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ", "                                 ", "         d   d     d   d         ", "         bCCCb     bCCCb         ", "  d     sbbbbbddsddbbbbbs     d  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbd dbbbd    ded    dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", "dbbbd dbbbd           dbbbd dbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbd dbbbd           dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC   d     d   CCC   CCC ", "dddd   dddCCCb     bCCCddd   dddd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", " ddd   ddd   d     d   ddd   ddd ", "   d   d                 d   d   ", " ddd   ddd   d     d   ddd   ddd ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", " ddd   ddd   d     d   ddd   ddd ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " CCC   CCC   d     d   CCC   CCC ", " CbC   CbC   d     d   CbC   CbC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC   d     d   CCCCCCCCC ", " CbC   CbC   d     d   CbC   CbC ", " CCC   CCC   d     d   CCC   CCC ", "                                 ")
            .aisle("  d     d      ded      d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ", " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ", " CCC   CCC             CCC   CCC ", "                                 ")
            .aisle("  d     d      ded      d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ", " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ", " CCC   CCC             CCC   CCC ", "                                 ")
            .aisle("  d     d     dsdsd     d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", "dbbbd dbbbd           dbbbd dbbbd", "dbbbdddbbbd           dbbbdddbbbd", " ddd   ddd             ddd   ddd ", "   d   d                 d   d   ", " ddd   ddd             ddd   ddd ", "dbbbdddbbbd           dbbbdddbbbd", "dbbbd dbbbd           dbbbd dbbbd", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ")
            .aisle("  d     d    deeeeed    d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ")
            .aisle(" dsdddddsddddseedeesddddsdddddsd ", "                d                ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ")
            .aisle("  deeeeedeeeededddedeeeedeeeeed  ", "               ddd               ", "                a                ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  s     s               s     s  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  s     s               s     s  ", "                                 ", "                                 ")
            .aisle(" dsdddddsddddseedeesddddsdddddsd ", "                d                ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ")
            .aisle("  d     d    deeeeed    d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "  d     d               d     d  ", "                                 ", "                                 ")
            .aisle("  d     d     dsdsd     d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", "dbbbd dbbbd           dbbbd dbbbd", "dbbbdddbbbd           dbbbdddbbbd", " ddd   ddd             ddd   ddd ", "   d   d                 d   d   ", " ddd   ddd             ddd   ddd ", "dbbbdddbbbd           dbbbdddbbbd", "dbbbd dbbbd           dbbbd dbbbd", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ")
            .aisle("  d     d      ded      d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ", " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ", " CCC   CCC             CCC   CCC ", "                                 ")
            .aisle("  d     d      ded      d     d  ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " CCC   CCC             CCC   CCC ", " CbC   CbC             CbC   CbC ", " CCCCCCCCC             CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC             CCCCCCCCC ", " CbC   CbC             CbC   CbC ", " CCC   CCC             CCC   CCC ", "                                 ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " CCC   CCC   d     d   CCC   CCC ", " CbC   CbC   d     d   CbC   CbC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " CCCCCCCCC   d     d   CCCCCCCCC ", " CbC   CbC   d     d   CbC   CbC ", " CCC   CCC   d     d   CCC   CCC ", "                                 ")
            .aisle("dbbbd dbbbd    ded    dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", "dbbbd dbbbd           dbbbd dbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbd dbbbd           dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC   d     d   CCC   CCC ", "dddd   dddCCCb     bCCCddd   dddd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", " ddd   ddd   d     d   ddd   ddd ", "   d   d                 d   d   ", " ddd   ddd   d     d   ddd   ddd ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", " ddd   ddd   d     d   ddd   ddd ")
            .aisle("dbbbd dbbbdddddsdsdddddbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "  s     s               s     s  ", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC   d     d   CbC   CbC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "  s     sbbbbbddsddbbbbbs     s  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ", "                                 ", "         d   d     d   d         ", "         bCCCb     bCCCb         ", "  d     sbbbbbddsddbbbbbs     d  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbdddbbbd    ded    dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", "dbbbdddbbbd           dbbbdddbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbdddbbbd           dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         dCCCd     dCCCd         ", "         dCCCd     dCCCd         ", "         dCCCd     dCCCd         ", "         bCCCb     bCCCb         ", "  d      bCCCb     bCCCb      d  ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   dd    d     d    dd   ddd ", "         d   d     d   d         ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "         dCCCd     dCCCd         ", "  d      d   d     d   d      d  ", "         d   d     d   d         ", "                                 ")
            .aisle("   d   d       ded       d   d   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   d   d                 d   d   ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "   d   d                 d   d   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   d   d                 d   d   ", "                                 ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "  s      dCCCd     dCCCd      s  ", "   s                         s   ", "                                 ", "                                 ")
            .aisle(" ddd   ddd     ded     ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "   C   C                 C   C   ", "   C   C                 C   C   ", "   C   C                 C   C   ", " ddd   dd    d     d    dd   ddd ", "         d   d     d   d         ", "         dCCCd     dCCCd         ", "                                 ", "                                 ", "                                 ", "  s      dCCCd     dCCCd      s  ", "   s     d   d     d   d     s   ", "         d   d     d   d         ", "                                 ")
            .aisle("dbbbdddbbbd    ded    dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", "dbbbdddbbbd           dbbbdddbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbdddbbbd           dbbbdddbbbd", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC             CCCCCCCCC ", " CCCCCCCCC   d     d   CCCCCCCCC ", "dbbbdddbbdCCCb     bCCCdbbdddbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         dCCCd     dCCCd         ", "  s      dCCCd     dCCCd      s  ", "  s      dCCCd     dCCCd      s  ", "         bCCCb     bCCCb         ", "    ss   bCCCb     bCCCb   ss    ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbd dbbbdddddsdsdddddbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", "dbbbd dbbbd           dbbbd dbbbd", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "  s     s               s     s  ", " ddd   ddd             ddd   ddd ", " ddd   ddd             ddd   ddd ", "dbbbd dbbbd           dbbbd dbbbd", " CbC   CbC             CbC   CbC ", " CbC   CbC             CbC   CbC ", " CbC   CbC   d     d   CbC   CbC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "  d     sbbbbbddsddbbbbbs     d  ", "  d      bCCCb     bCCCb      d  ", "  d      d   d     d   d      d  ", "   s                         s   ", "   s     d   d     d   d     s   ", "    ss   bCCCb     bCCCb   ss    ", "      dddbbbbbddsddbbbbbddd      ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle("dbbbd dbbbd    d d    dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", "dbbbd dbbbd           dbbbd dbbbd", "  d     d               d     d  ", "  d     d               d     d  ", "                                 ", "  d     d               d     d  ", "  d     d               d     d  ", "dbbbd dbbbd           dbbbd dbbbd", " CCC   CCC             CCC   CCC ", " CCC   CCC             CCC   CCC ", " CCC   CCC   d     d   CCC   CCC ", "dbbbd dbbdCCCb     bCCCdbbd dbbbd", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         d   d     d   d         ", "                                 ", "         d   d     d   d         ", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         bCCCb     bCCCb         ", "         d   d     d   d         ")
            .aisle(" ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", " ddd   ddd             ddd   ddd ", "                                 ", "                                 ", "                                 ", " ddd   ddd   d     d   ddd   ddd ", "         d   d     d   d         ", "         d   d     d   d         ", "                                 ", "                                 ", "                                 ", "         d   d     d   d         ", "         d   d     d   d         ", "         d   d     d   d         ", "                                 ");

    //////////////////////////////////////
    // *** Simple Machine ***//
    //////////////////////////////////////
    public static final MachineDefinition[] SEMI_FLUID_GENERATOR = GTMachineUtils.registerSimpleGenerator("semi_fluid",
            GTLCEuRecipeTypes.SEMI_FLUID_GENERATOR_FUELS, GTMachineUtils.genericGeneratorTankSizeFunction, 0.1f, GTValues.LV, GTValues.MV,
            GTValues.HV);

    public static final MachineDefinition[] ROCKET_ENGINE_GENERATOR = GTMachineUtils.registerSimpleGenerator("rocket_engine", GTRecipeTypes.get("rocket_engine"),
            GTMachineUtils.genericGeneratorTankSizeFunction, 0.1f, GTValues.EV, GTValues.IV, GTValues.LuV);
    public static final MachineDefinition[] NAQUADAH_REACTOR_GENERATOR = GTMachineUtils.registerSimpleGenerator("naquadah_reactor", GTRecipeTypes.get("naquadah_reactor"),
            GTMachineUtils.genericGeneratorTankSizeFunction, 0.1f, GTValues.IV, GTValues.LuV, GTValues.ZPM);

    public static final MachineDefinition[] LIGHTNING_PROCESSOR = GTMachineUtils.registerSimpleMachines("lightning_processor",
            GTLCEuRecipeTypes.LIGHTNING_PROCESSOR_RECIPES, GTMachineUtils.defaultTankSizeFunction);

    public static final MachineDefinition[] DEHYDRATOR = GTMachineUtils.registerSimpleMachines("dehydrator",
            GTLCEuRecipeTypes.DEHYDRATOR_RECIPES, GTMachineUtils.defaultTankSizeFunction);

    public static final MachineDefinition[] WORLD_DATA_SCANNER = GTMachineUtils.registerSimpleMachines("world_data_scanner",
            GTLCEuRecipeTypes.WORLD_DATA_SCANNER_RECIPES, tier -> 64000);

    public static final MachineDefinition[] NEUTRON_COMPRESSOR = GTMachineUtils.registerSimpleMachines("neutron_compressor",
            GTLCEuRecipeTypes.NEUTRON_COMPRESSOR_RECIPES, GTMachineUtils.defaultTankSizeFunction, false, GTValues.MAX);

    public static final MachineDefinition[] LIGHTNING_ROD = GTMachineUtils.registerTieredMachines(
            "lightning_rod",
            LightningRodMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Lightning Rod %s".formatted(GTValues.VLVH[tier], GTValues.VLVT[tier]))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> new SimpleGeneratorMachineRenderer(tier,
                            GTCEu.id("block/generators/lightning_rod")))
                    .tooltips(Component.translatable("gtceu.machine.lightning_rod.tooltip.0"))
                    .tooltips(Component.translatable("gtceu.machine.lightning_rod.tooltip.1"))
                    .tooltips(Component.translatable("gtceu.machine.lightning_rod.tooltip.2"))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.ampere_out", 512))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                            FormattingUtil.formatNumbers(GTValues.V[tier - 1]), GTValues.VNF[tier - 1]))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                            FormattingUtil.formatNumbers((long) (48828 * Math.pow(4, tier)))))
                    .tooltipBuilder(GTL_ADD)
                    .register(),
            GTValues.EV, GTValues.IV, GTValues.LuV);

    public static final MachineDefinition[] PRIMITIVE_MAGIC_ENERGY = GTMachineUtils.registerTieredMachines(
            "primitive_magic_energy",
            MagicEnergyMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Primitive Magic Energy %s".formatted(GTValues.VLVH[tier], GTValues.VLVT[tier]))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> new SimpleGeneratorMachineRenderer(tier,
                            GTCEu.id("block/generators/primitive_magic_energy")))
                    .tooltips(Component.translatable("gtceu.machine.primitive_magic_energy.tooltip.0"))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.ampere_out", 16))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                            FormattingUtil.formatNumbers(GTValues.V[tier]), GTValues.VNF[tier]))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                            FormattingUtil.formatNumbers(GTValues.V[tier] * 512L)))
                    .tooltipBuilder(GTL_ADD)
                    .register(),
            GTValues.ULV, GTValues.LV);

    private static MachineDefinition[] registerHugeFluidHatches(String name, String displayname, String model,
                                                                String tooltip, IO io, PartAbility... abilities) {
        return GTMachineUtils.registerTieredMachines(name,
                (holder, tier) -> new HugeFluidHatchPartMachine(holder, tier, io),
                (tier, builder) -> {
                    builder.langValue(GTValues.VNF[tier] + ' ' + displayname)
                            .rotationState(RotationState.ALL)
                            .overlayTieredHullRenderer(model)
                            .abilities(abilities)
                            //.compassNode("fluid_hatch")
                            .tooltips(Component.translatable("gtceu.machine." + tooltip + ".tooltip"));
                    builder.tooltips(Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity_mult",
                                    tier, FormattingUtil.formatNumbers(Integer.MAX_VALUE)))
                            .tooltipBuilder(GTL_ADD);
                    return builder.register();
                },
                GTValues.tiersBetween(GTValues.LV, GTValues.OpV));
    }

    //////////////////////////////////////
    // ********** Part **********//
    //////////////////////////////////////
    public static final MachineDefinition LARGE_STEAM_HATCH = REGISTRATE
            .machine("large_steam_input_hatch", holder -> new LargeSteamHatchPartMachine(holder, IO.IN, 8192, false))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.STEAM)
            .overlaySteamHullRenderer("steam_hatch")
            .tooltips(Component.translatable("gtceu.machine.large_steam_input_hatch.tooltip.0"),
                    Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity",
                            8192 * FluidHelper.getBucket()),
                    Component.translatable("gtceu.machine.steam.steam_hatch.tooltip"))
            .tooltipBuilder(GTL_ADD)
            //.compassSections(PartAbility.STEAM)
            //.compassNode("steam_hatch")
            .register();

    public static final MachineDefinition STERILE_CLEANING_MAINTENANCE_HATCH = REGISTRATE
            .machine("sterile_cleaning_maintenance_hatch",
                    holder -> new GTLCEuCleaningMaintenanceHatchPartMachine(holder, ICleaningRoom.STERILE_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.STERILE_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                    GTL_ADD.accept(stack, tooltips);
                }
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(7,
                    GTCEu.id("block/machine/part/maintenance.sterile_cleaning")))
            .register();

    public static final MachineDefinition LAW_CLEANING_MAINTENANCE_HATCH = REGISTRATE
            .machine("law_cleaning_maintenance_hatch",
                    holder -> new GTLCEuCleaningMaintenanceHatchPartMachine(holder, ICleaningRoom.LAW_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.LAW_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(
                    () -> new MaintenanceHatchPartRenderer(10, GTCEu.id("block/machine/part/maintenance.law_cleaning")))
            .register();

    public static final MachineDefinition AUTO_CONFIGURATION_MAINTENANCE_HATCH = REGISTRATE
            .machine("auto_configuration_maintenance_hatch", AutoConfigurationMaintenanceHatchPartMachine::new)
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"))
            .tooltipBuilder(GTL_ADD)
            .renderer(() -> new MaintenanceHatchPartRenderer(5, GTCEu.id("block/machine/part/maintenance.full_auto")))
            .register();

    public static final MachineDefinition CLEANING_CONFIGURATION_MAINTENANCE_HATCH = REGISTRATE
            .machine("cleaning_configuration_maintenance_hatch",
                    holder -> new CleaningConfigurationMaintenanceHatchPartMachine(holder, ICleaningRoom.DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(5, GTCEu.id("block/machine/part/maintenance.cleaning")))
            .register();

    public static final MachineDefinition STERILE_CONFIGURATION_CLEANING_MAINTENANCE_HATCH = REGISTRATE
            .machine("sterile_configuration_cleaning_maintenance_hatch",
                    holder -> new CleaningConfigurationMaintenanceHatchPartMachine(holder, ICleaningRoom.STERILE_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.STERILE_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(9,
                    GTCEu.id("block/machine/part/maintenance.sterile_cleaning")))
            .register();

    public static final MachineDefinition LAW_CONFIGURATION_CLEANING_MAINTENANCE_HATCH = REGISTRATE
            .machine("law_configuration_cleaning_maintenance_hatch",
                    holder -> new CleaningConfigurationMaintenanceHatchPartMachine(holder, ICleaningRoom.LAW_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.LAW_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(
                    () -> new MaintenanceHatchPartRenderer(12, GTCEu.id("block/machine/part/maintenance.law_cleaning")))
            .register();

    public static final MachineDefinition GRAVITY_HATCH = REGISTRATE
            .machine("gravity_hatch", GravityCleaningMaintenancePartMachine::new)
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"))
            .tooltipBuilder(GTL_ADD)
            .renderer(() -> new MaintenanceHatchPartRenderer(8,
                    GTCEu.id("block/machine/part/maintenance.full_auto")))
            .register();

    public static final MachineDefinition GRAVITY_CONFIGURATION_HATCH = REGISTRATE
            .machine("gravity_configuration_hatch", GravityCleaningConfigurationMaintenancePartMachine::new)
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"))
            .tooltipBuilder(GTL_ADD)
            .renderer(() -> new MaintenanceHatchPartRenderer(9,
                    GTCEu.id("block/machine/part/maintenance.full_auto")))
            .register();

    public static final MachineDefinition CLEANING_GRAVITY_CONFIGURATION_MAINTENANCE_HATCH = REGISTRATE
            .machine("cleaning_gravity_configuration_maintenance_hatch", holder -> new GravityCleaningConfigurationMaintenancePartMachine(holder, ICleaningRoom.DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(10, GTCEu.id("block/machine/part/maintenance.cleaning")))
            .register();

    public static final MachineDefinition STERILE_CLEANING_GRAVITY_CONFIGURATION_MAINTENANCE_HATCH = REGISTRATE
            .machine("sterile_cleaning_gravity_configuration_maintenance_hatch", holder -> new GravityCleaningConfigurationMaintenancePartMachine(holder, ICleaningRoom.STERILE_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.STERILE_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(11, GTCEu.id("block/machine/part/maintenance.cleaning")))
            .register();

    public static final MachineDefinition LAW_CLEANING_GRAVITY_CONFIGURATION_MAINTENANCE_HATCH = REGISTRATE
            .machine("law_cleaning_gravity_configuration_maintenance_hatch", holder -> new GravityCleaningConfigurationMaintenancePartMachine(holder, ICleaningRoom.LAW_DUMMY_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                for (CleanroomType type : ICleaningRoom
                        .getCleanroomTypes(ICleaningRoom.LAW_DUMMY_CLEANROOM)) {
                    tooltips.add(Component.literal("  ")
                            .append(Component.translatable(type.getTranslationKey())
                                    .withStyle(ChatFormatting.GREEN)));
                }
                GTL_ADD.accept(stack, tooltips);
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(12, GTCEu.id("block/machine/part/maintenance.cleaning")))
            .register();

    public static final MachineDefinition WIRELESS_DATA_HATCH_TRANSMITTER = REGISTRATE
            .machine("wireless_data_transmitter_hatch", (holder) -> new WirelessOpticalDataHatchMachine(holder, true))
            .rotationState(RotationState.ALL)
            //.compassNodeSelf()
            .abilities(PartAbility.OPTICAL_DATA_TRANSMISSION)
            .overlayTieredHullRenderer("optical_data_hatch")
            .tooltips(Component.translatable("gtceu.machine.wireless_data_transmitter_hatch.tooltip.1"),
                    Component.translatable("gtceu.machine.wireless_data_transmitter_hatch.tooltip.2"))
            .tier(LuV)
            .register();

    public static final MachineDefinition WIRELESS_DATA_HATCH_RECEIVER = REGISTRATE
            .machine("wireless_data_receiver_hatch", (holder) -> new WirelessOpticalDataHatchMachine(holder, false))
            .rotationState(RotationState.ALL)
            //.compassNodeSelf()
            .abilities(PartAbility.OPTICAL_DATA_RECEPTION)
            .overlayTieredHullRenderer("optical_data_hatch")
            .tooltips(Component.translatable("gtceu.machine.wireless_data_receiver_hatch.tooltip.1"),
                    Component.translatable("gtceu.machine.wireless_data_receiver_hatch.tooltip.2"))
            .tier(LuV)
            .register();

    public final static MachineDefinition[] HUGE_FLUID_IMPORT_HATCH = registerHugeFluidHatches("huge_input_hatch", "Huge Input Hatch", "fluid_hatch.import", "fluid_hatch.import", IO.IN, PartAbility.IMPORT_FLUIDS);

    public final static MachineDefinition[] HUGE_FLUID_EXPORT_HATCH = registerHugeFluidHatches("huge_output_hatch", "Huge Output Hatch", "fluid_hatch.export", "fluid_hatch.export", IO.OUT, PartAbility.EXPORT_FLUIDS);

    public static final MachineDefinition[] LASER_INPUT_HATCH_16384 = GTMachineUtils.registerLaserHatch(IO.IN, 16384,
            PartAbility.INPUT_LASER);
    public static final MachineDefinition[] LASER_OUTPUT_HATCH_16384 = GTMachineUtils.registerLaserHatch(IO.OUT, 16384,
            PartAbility.OUTPUT_LASER);
    public static final MachineDefinition[] LASER_INPUT_HATCH_65536 = GTMachineUtils.registerLaserHatch(IO.IN, 65536,
            PartAbility.INPUT_LASER);
    public static final MachineDefinition[] LASER_OUTPUT_HATCH_65536 = GTMachineUtils.registerLaserHatch(IO.OUT, 65536,
            PartAbility.OUTPUT_LASER);
    public static final MachineDefinition[] LASER_INPUT_HATCH_262144 = GTMachineUtils.registerLaserHatch(IO.IN, 262144,
            PartAbility.INPUT_LASER);
    public static final MachineDefinition[] LASER_OUTPUT_HATCH_262144 = GTMachineUtils.registerLaserHatch(IO.OUT, 262144,
            PartAbility.OUTPUT_LASER);
    public static final MachineDefinition[] LASER_INPUT_HATCH_1048576 = GTMachineUtils.registerLaserHatch(IO.IN, 1048576,
            PartAbility.INPUT_LASER);
    public static final MachineDefinition[] LASER_OUTPUT_HATCH_1048576 = GTMachineUtils.registerLaserHatch(IO.OUT, 1048576,
            PartAbility.OUTPUT_LASER);
    public static final MachineDefinition[] LASER_INPUT_HATCH_4194304 = GTMachineUtils.registerLaserHatch(IO.IN, 4194304,
            PartAbility.INPUT_LASER);
    public static final MachineDefinition[] LASER_OUTPUT_HATCH_4194304 = GTMachineUtils.registerLaserHatch(IO.OUT, 4194304,
            PartAbility.OUTPUT_LASER);

    static {
        GTMTRegistration.GTMTHINGS_REGISTRATE.creativeModeTab(() -> CreativeModeTabs.WIRELESS_TAB);
    }

    public static final MachineDefinition[] WIRELESS_ENERGY_INPUT_HATCH_64A = WirelessMachines.registerWirelessEnergyHatch(IO.IN, 64, PartAbility.INPUT_ENERGY, GTValues.tiersBetween(GTValues.EV, GTValues.MAX));
    public static final MachineDefinition[] WIRELESS_ENERGY_OUTPUT_HATCH_64A = WirelessMachines.registerWirelessEnergyHatch(IO.OUT, 64, PartAbility.OUTPUT_ENERGY, GTValues.tiersBetween(GTValues.EV, GTValues.MAX));

    public static final MachineDefinition[] WIRELESS_ENERGY_INPUT_HATCH_262144A = WirelessMachines.registerWirelessLaserHatch(IO.IN, 262144, PartAbility.INPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);
    public static final MachineDefinition[] WIRELESS_ENERGY_INPUT_HATCH_1048576A = WirelessMachines.registerWirelessLaserHatch(IO.IN, 1048576, PartAbility.INPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);
    public static final MachineDefinition[] WIRELESS_ENERGY_INPUT_HATCH_4194304A = WirelessMachines.registerWirelessLaserHatch(IO.IN, 4194304, PartAbility.INPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);
    public static final MachineDefinition[] WIRELESS_ENERGY_OUTPUT_HATCH_262144A = WirelessMachines.registerWirelessLaserHatch(IO.OUT, 262144, PartAbility.OUTPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);
    public static final MachineDefinition[] WIRELESS_ENERGY_OUTPUT_HATCH_1048576A = WirelessMachines.registerWirelessLaserHatch(IO.OUT, 1048576, PartAbility.OUTPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);
    public static final MachineDefinition[] WIRELESS_ENERGY_OUTPUT_HATCH_4194304A = WirelessMachines.registerWirelessLaserHatch(IO.OUT, 4194304, PartAbility.OUTPUT_LASER, WirelessMachines.WIRELL_ENERGY_HIGH_TIERS);

    public static final MachineDefinition[] NEUTRON_ACCELERATOR = GTMachineUtils.registerTieredMachines("neutron_accelerator",
            NeutronAcceleratorPartMachine::new,
            (tier, builder) -> builder
                    .langValue(GTValues.VNF[tier] + "Neutron Accelerator")
                    .rotationState(RotationState.ALL)
                    .abilities(GTLCEuPartAbility.NEUTRON_ACCELERATOR)
                    .tooltips(Component.translatable("gtceu.universal.tooltip.max_voltage_in", GTValues.V[tier], GTValues.VNF[tier]),
                            Component.translatable("gtceu.machine.neutron_accelerator.tooltip.0", GTValues.V[tier] * 8 / 10),
                            Component.translatable("gtceu.machine.neutron_accelerator.tooltip.1"),
                            Component.translatable("gtceu.universal.tooltip.energy_storage_capacity", 2 * GTValues.V[tier]))
                    .tooltipBuilder(GTL_ADD)
                    .overlayTieredHullRenderer("neutron_accelerator")
                    //.compassNode("neutron_accelerator")
                    .register(),
            GTMachineUtils.ALL_TIERS);

    public final static MachineDefinition NEUTRON_SENSOR = REGISTRATE
            .machine("neutron_sensor", NeutronSensorPartMachine::new)
            .langValue("Neutron Sensor")
            .tier(GTValues.IV)
            .rotationState(RotationState.ALL)
            .tooltips(Component.translatable("gtceu.machine.neutron_sensor.tooltip.0"))
            .tooltipBuilder(GTL_ADD)
            .overlayTieredHullRenderer("neutron_sensor")
            .register();

    public final static MachineDefinition HEAT_SENSOR = REGISTRATE
            .machine("heat_sensor", TemperatureSensorPartMachine::new)
            .langValue("Temperature Sensor")
            .tier(GTValues.UXV)
            .rotationState(RotationState.ALL)
            .tooltips(Component.translatable("gtceu.machine.heat_sensor.tooltip.0"))
            .tooltipBuilder(GTL_ADD)
            .overlayTieredHullRenderer("neutron_sensor")
            .register();

    public static final MachineDefinition ROTOR_HATCH = REGISTRATE
            .machine("rotor_hatch", RotorHatchPartMachine::new)
            .langValue("Rotor Hatch")
            .tier(GTValues.EV)
            .rotationState(RotationState.ALL)
            .overlayTieredHullRenderer("rotor_hatch")
            .register();

    public static final MachineDefinition BLOCK_BUS = REGISTRATE
            .machine("block_bus", BlockBusPartMachine::new)
            .tier(GTValues.LuV)
            .rotationState(RotationState.ALL)
            .renderer(() -> new OverlayTieredMachineRenderer(GTValues.LuV, GTCEu.id("block/machine/part/item_bus.import")))
            .register();

    public static final MachineDefinition TAG_FILTER_ME_STOCK_BUS_PART_MACHINE = REGISTRATE
            .machine("tag_filter_me_stock_bus_part_machine", TagFilterMEStockBusPartMachine::new)
            .tier(LuV)
            .abilities(PartAbility.IMPORT_ITEMS)
            .rotationState(RotationState.ALL)
            .renderer(() -> new OverlayTieredMachineRenderer(LuV, GTCEu.id("block/machine/part/me_item_bus.import")))
            .tooltips(
                    Component.translatable("gtceu.machine.item_bus.import.tooltip"),
                    Component.translatable("gtceu.machine.me.item_import.tooltip"),
                    Component.translatable("gtceu.machine.me.copy_paste.tooltip"),
                    Component.translatable("gtceu.universal.enabled"))
            //.compassNode("item_bus")
            .register();

    public static final MachineDefinition ME_DUAL_HATCH_STOCK_PART_MACHINE = REGISTRATE
            .machine("me_dual_hatch_stock_part_machine", MEDualHatchStockPartMachine::new)
            .tier(LuV)
            .abilities(PartAbility.IMPORT_ITEMS, PartAbility.IMPORT_FLUIDS)
            .rotationState(RotationState.ALL)
            .renderer(() -> new OverlayTieredMachineRenderer(LuV, GTCEu.id("block/machine/part/me_pattern_buffer")))
            .tooltips(
                    Component.translatable("gtceu.machine.dual_hatch.import.tooltip"),
                    Component.translatable("gtceu.machine.me.item_import.tooltip"),
                    Component.translatable("gtceu.universal.enabled"))
            //.compassNode("dual_hatch")
            .register();
}
