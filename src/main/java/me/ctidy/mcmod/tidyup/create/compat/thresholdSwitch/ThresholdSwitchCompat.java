/*
 * Copyright (c) 2024, Tidy-Bear.
 *
 * This file is part of "Tidy UP: Create".
 *
 * "Tidy UP: Create" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Tidy UP: Create" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Tidy UP: Create".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.tidyup.create.compat.thresholdSwitch;

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
