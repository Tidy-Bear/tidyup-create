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

package me.ctidy.mcmod.tidyup.create.space_indents;

import com.simibubi.create.foundation.utility.Components;
import me.ctidy.mcmod.tidyup.create.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * MixinFacade
 *
 * @author Tidy-Bear
 * @since 2024/7/30
 */
public final class MixinFacade {

    public static String explicitlyScaleSpacingAsString(String spacing) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return spacing;
        return SpaceIndentsUtil.scaledSpaceIndents(spacing.length());
    }

    public static MutableComponent explicitlyScaleIndentsAsComponent(String rawText) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) {
            return Components.literal(rawText);
        }
        return SpaceIndentsUtil.componentWithScaledSpaceIndents(rawText);
    }

    public static MutableComponent explicitlyScaleIndentsAsComponent(Component templateComponent) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) {
            return templateComponent.plainCopy();
        }
        return Component.literal(SpaceIndentsUtil.scaledSpaceIndents(4, templateComponent.getStyle()));
    }

    private MixinFacade() { }

}
