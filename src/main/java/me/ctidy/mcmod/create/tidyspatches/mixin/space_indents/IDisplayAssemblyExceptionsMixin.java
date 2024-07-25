package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.IDisplayAssemblyExceptions;
import com.simibubi.create.content.contraptions.bearing.ClockworkBearingBlockEntity;
import com.simibubi.create.content.contraptions.bearing.MechanicalBearingBlockEntity;
import com.simibubi.create.content.contraptions.bearing.WindmillBearingBlockEntity;
import com.simibubi.create.content.contraptions.elevator.ElevatorPulleyBlockEntity;
import com.simibubi.create.content.contraptions.gantry.GantryCarriageBlockEntity;
import com.simibubi.create.content.contraptions.mounted.CartAssemblerBlockEntity;
import com.simibubi.create.content.contraptions.piston.LinearActuatorBlockEntity;
import com.simibubi.create.content.contraptions.piston.MechanicalPistonBlockEntity;
import com.simibubi.create.content.contraptions.pulley.PulleyBlockEntity;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Arrays;
import java.util.List;

/**
 * IDisplayAssemblyExceptionsMixin
 *
 * @author ctidy
 * @since 2024/7/25
 */
@Mixin(value = {
        ClockworkBearingBlockEntity.class,
        MechanicalBearingBlockEntity.class,
        WindmillBearingBlockEntity.class,
        ElevatorPulleyBlockEntity.class,
        GantryCarriageBlockEntity.class,
        CartAssemblerBlockEntity.class,
        LinearActuatorBlockEntity.class,
        MechanicalPistonBlockEntity.class,
        PulleyBlockEntity.class
}, remap = false)
public abstract class IDisplayAssemblyExceptionsMixin implements IDisplayAssemblyExceptions {

    @Override
    public boolean addExceptionToTooltip(List<Component> tooltip) {
        AssemblyException e = getLastAssemblyException();
        if (e == null)
            return false;

        if (!tooltip.isEmpty())
            tooltip.add(Components.immutableEmpty());

        Lang.builder().translate("gui.assembly.exception")
                .style(ChatFormatting.GOLD)
                .forGoggles(tooltip);

        Arrays.stream(e.component.getString().split("\n"))
                        .forEach(l -> TooltipHelper.cutStringTextComponent(l, TooltipHelper.Palette.GRAY_AND_WHITE)
                                .forEach(c -> Lang.text(c.getString()).forGoggles(tooltip)));

        return true;
    }

}
