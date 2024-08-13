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

package me.ctidy.mcmod.tidyup.create.mixin.schematic_preview;

import com.simibubi.create.content.schematics.client.SchematicHandler;
import com.simibubi.create.content.schematics.client.SchematicRenderer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Vector;

/**
 * SchematicHandlerMixin
 *
 * @author Tidy-Bear
 * @since 2024/7/22
 */
@Mixin(value = SchematicHandler.class, remap = false)
public abstract class SchematicHandlerMixin {

    @Shadow private ItemStack activeSchematicItem;

    @Shadow private Vector<SchematicRenderer> renderers;

    @Inject(method = "itemLost", at = @At("HEAD"), cancellable = true)
    private void itemLostFromHotbar(Player player, CallbackInfoReturnable<Boolean> cir) {
        for (int i = 0; i < Inventory.getSelectionSize(); i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.isEmpty()) {
                // prevents itemStack and activeSchematicItem being same but both empty, after player 'q'-ed the schematic
                continue;
            }
            if (ItemStack.matches(activeSchematicItem, itemStack)) {
                cir.setReturnValue(false);
                return;
            }
        }
        cir.setReturnValue(true);
    }

    @Inject(method = "init", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lcom/simibubi/create/content/schematics/client/SchematicHandler;selectionScreen:Lcom/simibubi/create/content/schematics/client/ToolSelectionScreen;", ordinal = 1))
    private void deactivateRenderersUnlessDeployed(LocalPlayer player, ItemStack stack, CallbackInfo ci) {
        // fix previous deployed preview rendering at (0, 0, 0) after switching to another un-deployed schematic
        renderers.forEach(r -> r.setActive(false));
    }

}
