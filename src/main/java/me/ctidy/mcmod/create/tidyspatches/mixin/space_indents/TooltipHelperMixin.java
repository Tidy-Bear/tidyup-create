package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.foundation.item.TooltipHelper;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * TooltipHelperMixin
 *
 * @author ctidy
 * @since 2024/7/30
 */
@Mixin(value = TooltipHelper.class, remap = false)
public abstract class TooltipHelperMixin {

    @ModifyConstant(method = "makeProgressBar", constant = @Constant(stringValue = " "))
    private static String scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleSpacingAsString(legacySpacing);
    }

}
