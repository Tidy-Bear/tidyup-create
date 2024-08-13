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

package me.ctidy.mcmod.tidyup.create.mixin.space_indents.compat.create_central_kitchen;

import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import plus.dragons.createcentralkitchen.content.contraptions.blazeStove.BlazeStoveGuideItem;
import plus.dragons.createdragonlib.lang.LangBuilder;

import java.util.List;

/**
 * BlazeStoveGuideItemMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/30
 */
@Mixin(value = BlazeStoveGuideItem.class, remap = false)
@Pseudo
public abstract class BlazeStoveGuideItemMixin {

    @Redirect(method = "appendGuideTooltip", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonlib/lang/LangBuilder;forGoggles(Ljava/util/List;I)V"))
    private void fixIncorrectIndents(LangBuilder itemName, List<? super MutableComponent> tooltip, int indents) {
        itemName.forGoggles(tooltip, 1);
    }

    @Redirect(method = "appendGuideTooltip", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonlib/lang/LangBuilder;forGoggles(Ljava/util/List;)V", ordinal = 2))
    private void fixUnexpectedIndents(LangBuilder itemName, List<? super MutableComponent> tooltip) {
        itemName.addTo(tooltip);
    }

}
