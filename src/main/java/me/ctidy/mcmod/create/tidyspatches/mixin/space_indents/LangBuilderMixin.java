package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.foundation.utility.LangBuilder;
import joptsimple.internal.Strings;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.space_indents.SpaceIndentsUtil;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LangBuilderMixin
 *
 * @author ctidy
 * @since 2024/4/30
 */
@Mixin(value = LangBuilder.class, remap = false)
public abstract class LangBuilderMixin {

    @Shadow MutableComponent component;

    @Redirect(
            method = "forGoggles(Ljava/util/List;I)V",
            at = @At(value = "INVOKE", target = "Ljoptsimple/internal/Strings;repeat(CI)Ljava/lang/String;")
    )
    private String scaleSpaceIndents(char ch, int indents) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return Strings.repeat(ch, indents);
        return SpaceIndentsUtil.scaledSpaceIndents(indents, getStyle());
    }

    private Style getStyle() {
        return component == null ? Style.EMPTY : component.getStyle();
    }

}
