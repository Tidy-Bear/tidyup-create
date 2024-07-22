package me.ctidy.mcmod.create.tidyspatches.mixin;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.utils.SpaceIndentsUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GoggleOverlayRendererMixin
 *
 * @author ctidy
 * @since 2024/5/2
 */
@Mixin(value = GoggleOverlayRenderer.class, remap = false)
public abstract class GoggleOverlayRendererMixin {

    private static final Matcher MATCHER = Pattern.compile("^( +)(.*)$").matcher("");

    @Redirect(
            method = "renderOverlay",
            at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")
    )
    private static Iterator<Component> adjustSpaceIndents(List<Component> tooltip) {
        if (!ClientConfig.INSTANCE.enabled.get()) return tooltip.iterator();
        Minecraft mc = Minecraft.getInstance();
        if (SpaceIndentsUtil.isDefaultAdaptedToFont(mc.font)) return tooltip.iterator();
        for (int i = 0; i < tooltip.size(); i++) {
            Component c = tooltip.get(i);
            MutableComponent adjustedComponent = Component.empty().withStyle(c.getStyle());
            AtomicBoolean needMatch = new AtomicBoolean(true);
            c.visit((style, content) -> {
                if (content.isEmpty()) return Optional.empty();
                if (needMatch.get()) {
                    MATCHER.reset(content);
                    if (MATCHER.matches()) {
                        needMatch.set(false);
                        content = SpaceIndentsUtil.indentsTextAdaptedToFont(mc.font, MATCHER.end(1))
                                + MATCHER.group(2);
                    }
                }
                adjustedComponent.append(Component.literal(content).withStyle(style));
                return Optional.empty();
            }, Style.EMPTY);
            tooltip.set(i, adjustedComponent);
        }
        return tooltip.iterator();
    }

}
