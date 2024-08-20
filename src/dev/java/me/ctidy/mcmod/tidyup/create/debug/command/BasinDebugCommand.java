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

package me.ctidy.mcmod.tidyup.create.debug.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import me.ctidy.mcmod.tidyup.create.mixin.debug.BasinBlockEntityAccessor;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * BasinDebugCommand
 *
 * @author Tidy-Bear
 * @since 2024/8/19
 */
public final class BasinDebugCommand {

    static final SimpleCommandExceptionType ERROR_NOT_BASIN = new SimpleCommandExceptionType(Component.literal("Target block is not a block entity or is not a Basin."));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("debug_basin")
                .then(Commands.argument("targetPos", BlockPosArgument.blockPos())
                        .executes(cmd -> getSizeOfVisualizedItems(cmd, BlockPosArgument.getBlockPos(cmd, "targetPos")))
                )
        );
    }

    public static int getSizeOfVisualizedItems(CommandContext<CommandSourceStack> ctx, BlockPos pos) throws CommandSyntaxException {
        CommandSourceStack src = ctx.getSource();
        BlockEntity be = src.getLevel().getBlockEntity(pos);
        if (!(be instanceof BasinBlockEntity basin)) {
            throw ERROR_NOT_BASIN.create();
        }
        int size1 = ((BasinBlockEntityAccessor) basin).getSpoutputBuffer().size();
        int size2 = ((BasinBlockEntityAccessor) basin).getVisualizedItems().size();
        src.sendSuccess(() -> Component.literal(
                "Basin at " + pos + " :"
                        + "\nspoutputBuffer: " + size1
                        + "\nvisualizedItems: " + size2
        ), false);
        return size1 + size2;
    }

    private BasinDebugCommand() { }

}
