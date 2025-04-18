package org.qiuyeqaq.gtlcore_ceu.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class NotifiableCircuitItemStackHandler extends NotifiableItemStackHandler {

    public NotifiableCircuitItemStackHandler(MetaMachine machine) {
        super(machine, 1, IO.IN, IO.IN);
    }

    @NotNull
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate, boolean notifyChange) {
        if (GTItems.PROGRAMMED_CIRCUIT.isIn(stack)) {
            storage.setStackInSlot(slot, stack);
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }
}
