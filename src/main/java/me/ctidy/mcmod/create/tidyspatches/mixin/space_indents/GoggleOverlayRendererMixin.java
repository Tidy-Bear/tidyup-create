package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.space_indents.SpaceIndentsManager;
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
            MutableBoolean hasMatched = new MutableBoolean();
            c.visit((style, content) -> {
                if (content.isEmpty()) return Optional.empty();
                content = contentWithScaledSpaceIndents(content, hasMatched, style);
                adjustedComponent.append(Component.literal(content).withStyle(style));
                return Optional.empty();
            }, Style.EMPTY);
            tooltip.set(i, adjustedComponent);
        }
        return tooltip.iterator();
    }

    private static String contentWithScaledSpaceIndents(String content, MutableBoolean hasMatched, Style style) {
        if (hasMatched.isTrue() || content.charAt(0) != ' ') {
            return content;
        }
        hasMatched.setTrue();
        int i = 1;
        int length = content.length();
        while (i < length) {
            if (content.charAt(i) != ' ') {
                break;
            }
            i++;
        }
        if (i > length) {
            return SpaceIndentsManager.scaledSpaceIndents(length, style);
        }
        return SpaceIndentsManager.scaledSpaceIndents(length, style) + content.substring(i);
    }

}
