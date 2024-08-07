package me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;

/**
 * <h1>ThresholdSwitchCompat</h1>
 * <a href="https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb">Origin</a>
 * @author zelophed
 */
public interface ThresholdSwitchCompat {

	/**
	 * Whether apply this compat.
	 */
	boolean isFromThisMod(BlockEntity blockEntity);

	/**
	 * Gets the max size of the slot.
	 */
	long getSpaceInSlot(IItemHandler inv, int slot);

}
