package me.ctidy.mcmod.create.tidyspatches.mixin;

import com.simibubi.create.foundation.utility.LangBuilder;
import me.ctidy.mcmod.create.tidyspatches.utils.SpaceIndentsUtil;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

/**
 * LangBuilderMixin
 *
 * @deprecated So many usage not using {@link LangBuilder#forGoggles(List, int)}. Left here just for archive.
 *
 * @author ctidy
 * @since 2024/4/30
 */
@Mixin(value = LangBuilder.class, remap = false)
public abstract class LangBuilderMixin {

    @Redirect(
            method = "forGoggles(Ljava/util/List;I)V",
            at = @At(value = "INVOKE", target = "Ljoptsimple/internal/Strings;repeat(CI)Ljava/lang/String;")
    )
    public String adjustSpaceIndents(char ch, int indents) {
        Minecraft mc = Minecraft.getInstance();
        return SpaceIndentsUtil.indentsTextAdaptedToFont(mc.font, indents);
    }

}
