package me.ctidy.mcmod.create.moderngoggle.mixin;

import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import me.ctidy.mcmod.create.moderngoggle.config.ClientConfig;
import me.ctidy.mcmod.create.moderngoggle.utils.SpaceIndentsUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;
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
            String text = c.getContents();
            MATCHER.reset(text);
            if (!MATCHER.matches()) continue;
            text = SpaceIndentsUtil.indentsTextAdaptedToFont(mc.font, MATCHER.end(1))
                    + MATCHER.group(2);
            MutableComponent adjustedComponent = new TextComponent(text).withStyle(c.getStyle());
            c.getSiblings().forEach(adjustedComponent::append);
            tooltip.set(i, adjustedComponent);
        }
        return tooltip.iterator();
    }

}
