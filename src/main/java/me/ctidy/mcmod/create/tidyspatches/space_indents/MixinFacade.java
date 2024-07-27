package me.ctidy.mcmod.create.tidyspatches.space_indents;

import com.simibubi.create.foundation.utility.Components;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * MixinFacade
 *
 * @author ctidy
 * @since 2024/7/30
 */
public final class MixinFacade {

    public static String explicitlyScaleSpacingAsString(String spacing) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return spacing;
        return SpaceIndentsUtil.scaledSpaceIndents(spacing.length());
    }

    public static MutableComponent explicitlyScaleIndentsAsComponent(String rawText) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) {
            return Components.literal(rawText);
        }
        return SpaceIndentsUtil.componentWithScaledSpaceIndents(rawText);
    }

    public static MutableComponent explicitlyScaleIndentsAsComponent(Component templateComponent) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) {
            return templateComponent.plainCopy();
        }
        return Component.literal(SpaceIndentsUtil.scaledSpaceIndents(4, templateComponent.getStyle()));
    }

    private MixinFacade() { }

}
