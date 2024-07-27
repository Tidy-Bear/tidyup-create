package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.space_indents.SpaceIndentsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * GoggleOverlayRendererMixin
 *
 * @author ctidy
 * @since 2024/5/2
 */
@Mixin(value = GoggleOverlayRenderer.class, remap = false)
public abstract class GoggleOverlayRendererMixin {

    @Redirect(
            method = "renderOverlay",
            at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")
    )
    private static Iterator<Component> universalScaleSpaceIndents(List<Component> tooltip) {
        if (!ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return tooltip.iterator();
        for (int i = 0; i < tooltip.size(); i++) {
            Component c = tooltip.get(i);
            MutableComponent adjustedComponent = Component.empty().withStyle(c.getStyle());
            MutableBoolean reachFirstNonSpace = new MutableBoolean();
            c.visit((style, content) -> {
                if (content.isEmpty()) return Optional.empty();
                if (reachFirstNonSpace.isTrue()) {
                    adjustedComponent.append(Component.literal(content).withStyle(style));
                    return Optional.empty();
                }
                adjustedComponent.append(SpaceIndentsUtil.componentWithScaledSpaceIndents(content, style));
                if (SpaceIndentsUtil.indexOfNonSpace(content) < content.length() - 1) {
                    reachFirstNonSpace.setTrue();
                }
                return Optional.empty();
            }, Style.EMPTY);
            tooltip.set(i, adjustedComponent);
        }
        return tooltip.iterator();
    }

}
