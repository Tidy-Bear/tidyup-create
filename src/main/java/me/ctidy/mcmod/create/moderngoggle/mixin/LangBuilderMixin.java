package me.ctidy.mcmod.create.moderngoggle.mixin;

import com.simibubi.create.foundation.utility.LangBuilder;
import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
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

    private static final float VANILLA_SPACE_WIDTH = 4.0F;

    @Redirect(at = @At(value = "INVOKE", target = "Ljoptsimple/internal/Strings;repeat(CI)Ljava/lang/String;"), method = "forGoggles(Ljava/util/List;I)V")
    public String removeSpaceIndent(char ch, int indents) {
        int actualSpaceWidth = Minecraft.getInstance().font.width(" ");
        if (VANILLA_SPACE_WIDTH == actualSpaceWidth) return Strings.repeat(ch, indents);
        int actualIndents = Mth.ceil(VANILLA_SPACE_WIDTH * indents / actualSpaceWidth);
        return Strings.repeat(ch, actualIndents);
    }

}
