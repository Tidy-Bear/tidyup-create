package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.createaddition;

import com.mrh0.createaddition.blocks.connector.base.AbstractConnectorBlockEntity;
import com.mrh0.createaddition.blocks.redstone_relay.RedstoneRelayBlockEntity;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * EnergyUsageLegacySpacingMixin
 *
 * @author ctidy
 * @since 2024/7/30
 */
@Mixin(value = {
        AbstractConnectorBlockEntity.class,
        RedstoneRelayBlockEntity.class
}, remap = false)
@Pseudo
public abstract class EnergyUsageLegacySpacingMixin {

    @ModifyConstant(method = "addToGoggleTooltip", constant = @Constant(stringValue = " ", ordinal = 1))
    private String scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleSpacingAsString(legacySpacing);
    }

}
