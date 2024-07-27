package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.createaddition;

import com.mrh0.createaddition.blocks.alternator.AlternatorBlockEntity;
import com.mrh0.createaddition.blocks.connector.base.AbstractConnectorBlockEntity;
import com.mrh0.createaddition.blocks.electric_motor.ElectricMotorBlockEntity;
import com.mrh0.createaddition.blocks.modular_accumulator.ModularAccumulatorBlockEntity;
import com.mrh0.createaddition.blocks.redstone_relay.RedstoneRelayBlockEntity;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingMixin
 *
 * @author ctidy
 * @since 2024/7/27
 */
@Mixin(value = {
        AbstractConnectorBlockEntity.class,
        AlternatorBlockEntity.class,
        ElectricMotorBlockEntity.class,
        ModularAccumulatorBlockEntity.class,
        RedstoneRelayBlockEntity.class
}, remap = false)
@Pseudo
public abstract class LegacySpacingMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;literal(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 25,
            require = 0
    )
    private MutableComponent scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacing);
    }

}
