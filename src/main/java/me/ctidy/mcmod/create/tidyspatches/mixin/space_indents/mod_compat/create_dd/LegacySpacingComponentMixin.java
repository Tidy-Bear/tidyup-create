package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.create_dd;

import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uwu.lopyluna.create_dd.content.blocks.kinetics.multimeter.MultiMeterBlockEntity;

/**
 * LegacySpacingComponentMixin
 *
 * @author ctidy
 * @since 2024/7/27
 */
@Mixin(value = MultiMeterBlockEntity.class, remap = false)
@Pseudo
public abstract class LegacySpacingComponentMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 1,
            require = 0
    )
    private MutableComponent scaleIndents(Component legacySpacingComponent) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacingComponent);
    }

}
