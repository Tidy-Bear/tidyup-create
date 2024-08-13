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

package me.ctidy.mcmod.tidyup.create.mixin.space_indents;

import com.simibubi.create.foundation.item.TooltipHelper;
import me.ctidy.mcmod.tidyup.create.space_indents.MixinFacade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * TooltipHelperMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/30
 */
@Mixin(value = TooltipHelper.class, remap = false)
public abstract class TooltipHelperMixin {

    @ModifyConstant(method = "makeProgressBar", constant = @Constant(stringValue = " "))
    private static String scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleSpacingAsString(legacySpacing);
    }

}
