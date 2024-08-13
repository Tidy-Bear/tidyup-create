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

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import me.ctidy.mcmod.tidyup.create.config.ClientConfig;
import me.ctidy.mcmod.tidyup.create.space_indents.SpaceIndentsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * GoggleOverlayRendererMixin
 *
 * @author Tidy-Bear
 * @since 2024/5/2
 */
@Mixin(value = GoggleOverlayRenderer.class, remap = false)
public abstract class GoggleOverlayRendererMixin {

    @Redirect(
            method = "renderOverlay",
            at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")
    )
    private static Iterator<Component> universalScaleSpaceIndents(List<Component> tooltip) {
        if (!ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return tooltip.iterator();
        for (int i = 0; i < tooltip.size(); i++) {
            Component c = tooltip.get(i);
            MutableComponent adjustedComponent = Component.empty().withStyle(c.getStyle());
            MutableBoolean reachFirstNonSpace = new MutableBoolean();
            c.visit((style, content) -> {
                if (content.isEmpty()) return Optional.empty();
                if (reachFirstNonSpace.isTrue()) {
                    adjustedComponent.append(Component.literal(content).withStyle(style));
                    return Optional.empty();
                }
                adjustedComponent.append(SpaceIndentsUtil.componentWithScaledSpaceIndents(content, style));
                if (SpaceIndentsUtil.indexOfNonSpace(content) < content.length() - 1) {
                    reachFirstNonSpace.setTrue();
                }
                return Optional.empty();
            }, Style.EMPTY);
            tooltip.set(i, adjustedComponent);
        }
        return tooltip.iterator();
    }

}
