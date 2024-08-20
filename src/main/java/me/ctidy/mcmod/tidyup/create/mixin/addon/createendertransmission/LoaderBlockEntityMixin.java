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

package me.ctidy.mcmod.tidyup.create.mixin.addon.createendertransmission;

import com.forsteri.createendertransmission.blocks.chunkLoader.LoaderBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LoaderBlockEntityMixin
 *
 * @author Tidy-Bear
 * @since 2024/8/20
 */
@Mixin(value = LoaderBlockEntity.class, remap = false)
@Pseudo
public abstract class LoaderBlockEntityMixin {

    @Redirect(method = "addToGoggleTooltip", at = @At(value = "INVOKE", target = "Ljava/lang/Math;abs(F)F"))
    private float limitMaxRadius(float a) {
        return Math.min(Math.abs(a), 256);
    }

}
