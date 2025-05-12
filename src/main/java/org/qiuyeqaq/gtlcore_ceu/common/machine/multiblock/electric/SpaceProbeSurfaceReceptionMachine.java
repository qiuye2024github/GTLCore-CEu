package org.qiuyeqaq.gtlcore_ceu.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.qiuyeqaq.gtlcore_ceu.common.data.GTLCEuMaterials;

public class SpaceProbeSurfaceReceptionMachine extends WorkableElectricMultiblockMachine {

    protected ConditionalSubscriptionHandler checkSub;
    private int time;

    public SpaceProbeSurfaceReceptionMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        checkSub = new ConditionalSubscriptionHandler(this, this::check, this::isFormed);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        checkSub.initialize(getLevel());
    }

    private void check() {
        if (getOffsetTimer() % 20 == 0) {
            time++;
        }

        if (time < 30) {
            return;
        }
        time = 0;
        Level level = getLevel();
        BlockPos pos = getPos();
        BlockPos[] coordinates = new BlockPos[] { pos.offset(4, 8, 0), pos.offset(-4, 8, 0), pos.offset(0, 8, 4), pos.offset(0, 8, -4) };
        for (BlockPos a : coordinates) {
            if (level != null && level.getBlockState(a).is(ChemicalHelper.getBlock(TagPrefix.frameGt, GTLCEuMaterials.BlackTitanium))) {
                for (int i = -6; i < 7; i++) {
                    for (int j = -6; j < 7; j++) {
                        if (level.getBrightness(LightLayer.SKY, a.offset(0, 1, 0)) == 0) {
                            getRecipeLogic().interruptRecipe();
                            return;
                        }
                    }
                }
                return;
            }
        }
        getRecipeLogic().interruptRecipe();
    }
}
