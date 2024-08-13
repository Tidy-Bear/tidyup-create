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

package me.ctidy.mcmod.tidyup.create.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * IMod
 *
 * @author Tidy-Bear
 * @since 2024/8/7
 */
public interface IMod {

    /**
     * @return the mod id
     */
    String id();

    default ResourceLocation rl(String path) {
        return new ResourceLocation(id(), path);
    }

    default Block getBlock(String id) {
        return ForgeRegistries.BLOCKS.getValue(rl(id));
    }

    /**
     * @return a boolean of whether the mod is loaded or not based on mod id
     */
    default boolean isLoaded() {
        return ModList.get().isLoaded(id());
    }

    /**
     * Simple hook to run code if a mod is installed
     * @param toRun will be run only if the mod is loaded
     * @return Optional.empty() if the mod is not loaded, otherwise an Optional of the return value of the given supplier
     */
    default <T> Optional<T> runIfInstalled(Supplier<Supplier<T>> toRun) {
        if (isLoaded())
            return Optional.of(toRun.get().get());
        return Optional.empty();
    }

    /**
     * Simple hook to execute code if a mod is installed
     * @param toExecute will be executed only if the mod is loaded
     */
    default void executeIfInstalled(Supplier<Runnable> toExecute) {
        if (isLoaded()) {
            toExecute.get().run();
        }
    }

}
