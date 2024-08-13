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

package me.ctidy.mcmod.tidyup.create.mixin.space_indents.compat.create_dd;

import me.ctidy.mcmod.tidyup.create.space_indents.MixinFacade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uwu.lopyluna.create_dd.content.blocks.kinetics.multimeter.MultiMeterBlockEntity;

/**
 * LegacySpacingComponentMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/27
 */
@Mixin(value = MultiMeterBlockEntity.class, remap = false)
@Pseudo
public abstract class LegacySpacingComponentMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 1,
            require = 0
    )
    private MutableComponent scaleIndents(Component legacySpacingComponent) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacingComponent);
    }

}
