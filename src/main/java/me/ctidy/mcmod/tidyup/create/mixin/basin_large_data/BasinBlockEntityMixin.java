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

import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import com.simibubi.create.foundation.utility.IntAttached;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * BasinBlockEntityMixin
 *
 * @author Tidy-Bear
 * @since 2024/8/19
 */
@Mixin(value = BasinBlockEntity.class, remap = false)
public abstract class BasinBlockEntityMixin {

    @Shadow List<IntAttached<ItemStack>> visualizedOutputItems;
    @Shadow List<IntAttached<FluidStack>> visualizedOutputFluids;

    /**
     * Clears all visualized objects in the server.
     * <br/><br/>
     * If there are players nearby the Basin, the visualized objects in the server
     *   will be sent to those players and then cleared during the chunk ticking,
     *   which is prior to the block entities ticking.
     * <br/>
     * Conversely, if there are still visualized objects remaining, it indicates that
     *   no target players are nearby. Just feel free to clear those outdated things from the last 10 ticks at the lazy tick.
     * <br/><br/>
     * A detailed timeline is shown below:
     * <br/>
     * <br/>
     * <pre>
     * tick server level {
     *     tick chunks {
     *         broadcast changes, i.e. send update packets to target players
     *         visualized objects will be cleared once update NBT is written
     *     }
     *     tick block entities {
     *         // ordered by created time of each block entity
     *         tick {@link BasinOperatingBlockEntity} {
     *             apply basin recipe {
     *                 BasinBlockEntity.acceptOutputs {
     *                     extract outputs into {@link BasinBlockEntity#spoutputBuffer}
     *                 }
     *             }
     *         }
     *         tick {@link BasinBlockEntity} {
     *             lazy tick {  // every 10 ticks
     *                 clear outdated visualized objects // here it is
     *                 update spoutput {
     *                     acceptOutputs {
     *                         extract outputs into {@link BasinBlockEntity#spoutputBuffer}
     *                     }
     *                 }
     *             }
     *             tryClearingSpoutputOverflow {
     *                 move outputs from {@link BasinBlockEntity#spoutputBuffer} into visualized objects,
     *                 waiting for the next change broadcast of the chunk
     *             }
     *         }
     *     }
     * }
     * </pre>
     */
    @Inject(method = "lazyTick", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;updateSpoutput()V"))
    private void clearVisualizedObjects(CallbackInfo ci) {
        // no need to tick in server, just clear all
        // tickVisualizedOutputs();

        visualizedOutputFluids.clear();
        visualizedOutputItems.clear();
    }

}
