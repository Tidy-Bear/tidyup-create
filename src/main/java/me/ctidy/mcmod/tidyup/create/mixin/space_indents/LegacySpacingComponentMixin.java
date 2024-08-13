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

import com.simibubi.create.content.kinetics.gauge.GaugeBlockEntity;
import com.simibubi.create.content.logistics.tunnel.BrassTunnelBlockEntity;
import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import me.ctidy.mcmod.tidyup.create.space_indents.MixinFacade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingComponentMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/25
 */
@Mixin(value = {
        GaugeBlockEntity.class,
        BrassTunnelBlockEntity.class,
        AnalogLeverBlockEntity.class
}, remap = false)
public abstract class LegacySpacingComponentMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 5,
            require = 0
    )
    private MutableComponent scaleIndents(Component legacySpacingComponent) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacingComponent);
    }

}
