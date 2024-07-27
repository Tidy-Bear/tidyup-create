package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.create_enchantment_industry;

import me.ctidy.mcmod.create.tidyspatches.space_indents.MixinFacade;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import plus.dragons.createenchantmentindustry.content.contraptions.enchanting.enchanter.BlazeEnchanterBlockEntity;

/**
 * LegacySpacingMixin
 *
 * @author ctidy
 * @since 2024/7/27
 */
@Mixin(
        value = BlazeEnchanterBlockEntity.class,
        targets = {
                "plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer.PrintEntries$EnchantedBook",
                "plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer.PrintEntries$WrittenBook",
                "plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer.PrintEntries$NameTag",
                "plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer.PrintEntries$Schedule",
                "plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer.PrintEntries$ClipBoard",
                "plus.dragons.createenchantmentindustry.compat.quark.QuarkCompat$1"
        },
        remap = false
)
@Pseudo
public abstract class LegacySpacingMixin {

    @Redirect(
            method = "addToGoggleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;literal(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;", remap = true),
            // allow = 18,
            require = 0
    )
    private MutableComponent scaleIndents(String legacySpacing) {
        return MixinFacade.explicitlyScaleIndentsAsComponent(legacySpacing);
    }

}
