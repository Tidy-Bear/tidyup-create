package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import com.simibubi.create.foundation.item.TooltipHelper;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingComponentStaticMixin
 *
 * @author ctidy
 * @since 2024/7/25
 */
@Mixin(value = {GoggleOverlayRenderer.class, TooltipHelper.class }, remap = false)
public abstract class LegacySpacingComponentStaticMixin {

    @Redirect(
            method = {"renderOverlay", "addHint"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 4,
            require = 0
    )
    private static MutableComponent scaleIndents(Component legacySpacingComponent) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacingComponent);
    }

}
