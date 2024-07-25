package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import com.simibubi.create.foundation.item.TooltipHelper;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.space_indents.SpaceIndentsManager;
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
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;plainCopy()Lnet/minecraft/network/chat/MutableComponent;"),
            require = 0,
            allow = 4
    )
    private static MutableComponent staticAdjustSpaceIndentsForComponent(Component legacySpacingComponent) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return legacySpacingComponent.plainCopy();
        return Component.literal(SpaceIndentsManager.scaledSpaceIndents(4, legacySpacingComponent.getStyle()));
    }

}
