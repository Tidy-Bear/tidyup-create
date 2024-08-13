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

import me.ctidy.mcmod.tidyup.create.compat.Mods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.items.IItemHandler;

/**
 * <h1>SophisticatedStorage</h1>
 * <a href="https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb">Origin</a>
 * @author zelophed
 */
public class SophisticatedStorage implements ThresholdSwitchCompat {

    @Override
    public boolean isFromThisMod(BlockEntity be) {
        if (be == null)
            return false;

        ResourceLocation key = BlockEntityType.getKey(be.getType());
        if (key == null)
            return false;

        String namespace = key.getNamespace();
        return Mods.SOPHISTICATEDSTORAGE.id().equals(namespace)
                || Mods.SOPHISTICATEDBACKPACKS.id().equals(namespace);
    }

    @Override
    public long getSpaceInSlot(IItemHandler inv, int slot) {
        return ((long) inv.getSlotLimit(slot) * inv.getStackInSlot(slot).getMaxStackSize()) / 64;
    }

}
