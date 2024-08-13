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

package me.ctidy.mcmod.tidyup.create.mixin.space_indents.compat.create_dragon_lib;

import joptsimple.internal.Strings;
import me.ctidy.mcmod.tidyup.create.config.ClientConfig;
import me.ctidy.mcmod.tidyup.create.space_indents.SpaceIndentsUtil;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import plus.dragons.createdragonlib.lang.LangBuilder;

/**
 * LangBuilderMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/27
 */
@Mixin(value = LangBuilder.class, remap = false)
@Pseudo
public abstract class LangBuilderMixin {

    @Shadow MutableComponent component;

    @Redirect(
            method = "forGoggles(Ljava/util/List;I)V",
            at = @At(value = "INVOKE", target = "Ljoptsimple/internal/Strings;repeat(CI)Ljava/lang/String;")
    )
    private String scaleSpaceIndents(char ch, int indents) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return Strings.repeat(ch, indents);
        return SpaceIndentsUtil.scaledSpaceIndents(indents, getStyle());
    }

    private Style getStyle() {
        return component == null ? Style.EMPTY : component.getStyle();
    }

}
