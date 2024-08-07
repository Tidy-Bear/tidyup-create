package me.ctidy.mcmod.create.tidyspatches.mixin.thresholdSwitch_compat;

import com.simibubi.create.content.redstone.thresholdSwitch.ThresholdSwitchBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.InvManipulationBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.TankManipulationBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.VersionedInventoryTrackerBehaviour;
import me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch.FunctionalStorage;
import me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch.SophisticatedStorage;
import me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch.StorageDrawers;
import me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch.ThresholdSwitchCompat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

/**
 * <h1>ThresholdSwitchMixin</h1>
 * Main codes come from
 * <a href="https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb">here</a>.
 * @author ctidy
 * @author zelophed
 * @since 2024/8/7
 */
@Mixin(value = ThresholdSwitchBlockEntity.class, remap = false)
public abstract class ThresholdSwitchBlockEntityMixin {

    @Shadow public float currentLevel;
    @Shadow private FilteringBehaviour filtering;
    @Shadow private InvManipulationBehaviour observedInventory;
    @Shadow private TankManipulationBehaviour observedTank;
    @Shadow private VersionedInventoryTrackerBehaviour invVersionTracker;

    private static final List<ThresholdSwitchCompat> COMPAT = List.of(
            new FunctionalStorage(),
            new SophisticatedStorage(),
            new StorageDrawers()
    );

    @Redirect(method = "updateCurrentLevel", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/compat/storageDrawers/StorageDrawers;isDrawer(Lnet/minecraft/world/level/block/entity/BlockEntity;)Z"))
    private boolean countStockAndUpdate(BlockEntity targetBlockEntity) {
        boolean counted = false;
        float occupied = 0;
        float totalSpace = 0;
        if (observedInventory.hasInventory()) {
            counted = true;

            // Item inventory
            IItemHandler inv = observedInventory.getInventory();
            if (invVersionTracker.stillWaiting(inv)) {
                occupied = currentLevel;  // prevLevel
                totalSpace = 1.0F;
            } else {
                invVersionTracker.awaitNewVersion(inv);
                //noinspection ConstantConditions
                for (int slot = 0; slot < inv.getSlots(); slot++) {
                    ItemStack stackInSlot = inv.getStackInSlot(slot);
                    int finalSlot = slot;

                    long space = COMPAT
                            .stream()
                            .filter(compat -> compat.isFromThisMod(targetBlockEntity))
                            .map(compat -> compat.getSpaceInSlot(inv, finalSlot))
                            .findFirst()
                            .orElseGet(() -> (long) Math.min(stackInSlot.getMaxStackSize(), inv.getSlotLimit(finalSlot)));

                    int count = stackInSlot.getCount();
                    if (space == 0)
                        continue;

                    totalSpace += 1;
                    if (filtering.test(stackInSlot))
                        occupied += count * (1f / space);
                }
            }
        }

        if (observedTank.hasInventory()) {
            counted = true;

            // Fluid inventory
            IFluidHandler tank = observedTank.getInventory();
            //noinspection ConstantConditions
            for (int slot = 0; slot < tank.getTanks(); slot++) {
                FluidStack stackInSlot = tank.getFluidInTank(slot);
                int space = tank.getTankCapacity(slot);
                int count = stackInSlot.getAmount();
                if (space == 0)
                    continue;

                totalSpace += 1;
                if (filtering.test(stackInSlot))
                    occupied += count * (1f / space);
            }
        }

        if (counted) {
            currentLevel = occupied / totalSpace;
        }

        return false;
    }

    @Redirect(method = "updateCurrentLevel", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/blockEntity/behaviour/inventory/InvManipulationBehaviour;hasInventory()Z", ordinal = 2))
    private boolean avoidDuplicatedCount(InvManipulationBehaviour observedInventory) {
        return false;
    }

    @Redirect(method = "updateCurrentLevel", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/blockEntity/behaviour/inventory/TankManipulationBehaviour;hasInventory()Z", ordinal = 1))
    private boolean avoidDuplicatedCount(TankManipulationBehaviour observedTank) {
        return false;
    }

    @Redirect(method = "updateCurrentLevel", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/redstone/thresholdSwitch/ThresholdSwitchBlockEntity;currentLevel:F", opcode = Opcodes.PUTFIELD, ordinal = 2))
    private void avoidDuplicatedUpdate(ThresholdSwitchBlockEntity inst, float currentLevel) {
        // do nothing
    }

}
