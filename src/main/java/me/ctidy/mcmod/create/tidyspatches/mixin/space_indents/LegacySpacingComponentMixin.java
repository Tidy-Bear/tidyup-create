package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.kinetics.gauge.GaugeBlockEntity;
import com.simibubi.create.content.logistics.tunnel.BrassTunnelBlockEntity;
import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingComponentMixin
 *
 * @author ctidy
 * @since 2024/7/25
 */
@Mixin(value = {
        GaugeBlockEntity.class,
        BrassTunnelBlockEntity.class,
        AnalogLeverBlockEntity.class
}, remap = false)
public abstract class LegacySpacingComponentMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 5,
            require = 0
    )
    private MutableComponent scaleIndents(Component legacySpacingComponent) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacingComponent);
    }

}
