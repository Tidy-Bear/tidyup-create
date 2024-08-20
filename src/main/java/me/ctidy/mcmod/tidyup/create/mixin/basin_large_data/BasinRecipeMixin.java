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

package me.ctidy.mcmod.tidyup.create.mixin.basin_large_data;

import com.simibubi.create.content.processing.basin.BasinRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.List;

/**
 * BasinRecipeMixin
 *
 * @author Tidy-Bear
 * @since 2024/8/19
 */
@Mixin(value = BasinRecipe.class, remap = false)
public abstract class BasinRecipeMixin {

    private static boolean nonEmptyItemOrFluid(Object o) {
        return (o instanceof ItemStack is && !is.isEmpty()) || (o instanceof FluidStack fs && !fs.isEmpty());
    }

    @Redirect(
            method = "apply(Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;Lnet/minecraft/world/item/crafting/Recipe;Z)Z",
            at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z")
    )
    private static boolean addNonEmptyResults(List<Object> list, Collection<?> subCollection) {
        boolean success = false;
        for (Object o : subCollection) {
            if (nonEmptyItemOrFluid(o)) {
                success |= list.add(o);
            }
        }
        return success;
    }

    @Redirect(
            method = "apply(Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;Lnet/minecraft/world/item/crafting/Recipe;Z)Z",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    @SuppressWarnings("ConstantConditions")
    private static boolean addNonEmptyResults(List<Object> list, Object element) {
        return nonEmptyItemOrFluid(element) && list.add(element);
    }

}
