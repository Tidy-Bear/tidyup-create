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

package me.ctidy.mcmod.tidyup.create.mixin.schematic_better_tooltips;

import com.simibubi.create.content.schematics.SchematicItem;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SchematicItemMixin
 *
 * @author Tidy-Bear
 * @since 2024/8/7
 */
@Mixin(value = SchematicItem.class, remap = false)
public abstract class SchematicItemMixin {

    @Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, shift = At.Shift.AFTER, remap = false), remap = true)
    private void appendMoreInfoIntoTooltip(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag, CallbackInfo ci) {
        CompoundTag tag = stack.getTag();
        //noinspection ConstantConditions
        if (tag.contains("Bounds")) {
            ListTag list = tag.getList("Bounds", Tag.TAG_INT);
            String sizeText = list.stream()
                    .map(t -> String.valueOf(((IntTag) t).getAsInt()))
                    .collect(Collectors.joining("×"));
            tooltip.add(Components.literal(sizeText).withStyle(ChatFormatting.GOLD));
        }
    }

}
