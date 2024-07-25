package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import com.simibubi.create.content.fluids.tank.BoilerData;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.space_indents.SpaceIndentsManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * LegacySpacingMixin
 *
 * @author ctidy
 * @since 2024/7/25
 */
@Mixin(value = {WhistleBlockEntity.class, BoilerData.class}, remap = false)
public abstract class LegacySpacingMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/utility/Components;literal(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"),
            require = 0,
            allow = 3
    )
    public MutableComponent adjustSpaceIndentsForComponent(String legacySpacing) {
        if (ClientConfig.INSTANCE.spaceIndentsUniversalFix.get()) return Component.literal(legacySpacing);
        return Component.literal(SpaceIndentsManager.scaledSpaceIndents(legacySpacing.length()));
    }

}
