package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.createoreexcavation;

import com.tom.createores.block.entity.ExcavatingBlockEntity;
import com.tom.createores.block.entity.ExcavatingBlockEntityImpl;
import com.tom.createores.block.entity.ExtractorBlockEntity;
import com.tom.createores.block.entity.SampleDrillBlockEntity;
import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingMixin
 *
 * @author ctidy
 * @since 2024/7/27
 */
@Mixin(value = {
        ExcavatingBlockEntity.class,
        ExcavatingBlockEntityImpl.class,
        ExtractorBlockEntity.class,
        SampleDrillBlockEntity.class
}, remap = false)
@Pseudo
public abstract class LegacySpacingMixin {

    @Redirect(
            method = "addToGoggleTooltip*",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;literal(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 20,
            require = 0
    )
    private MutableComponent scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacing);
    }

}
