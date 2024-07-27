package me.ctidy.mcmod.create.tidyspatches.mixin.space_indents.mod_compat.create_central_kitchen;

import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import plus.dragons.createcentralkitchen.content.contraptions.blazeStove.BlazeStoveGuideItem;
import plus.dragons.createdragonlib.lang.LangBuilder;

import java.util.List;

/**
 * BlazeStoveGuideItemMixin
 *
 * @author ctidy
 * @since 2024/7/30
 */
@Mixin(value = BlazeStoveGuideItem.class, remap = false)
@Pseudo
public abstract class BlazeStoveGuideItemMixin {

    @Redirect(method = "appendGuideTooltip", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonlib/lang/LangBuilder;forGoggles(Ljava/util/List;I)V"))
    private void fixIncorrectIndents(LangBuilder itemName, List<? super MutableComponent> tooltip, int indents) {
        itemName.forGoggles(tooltip, 1);
    }

    @Redirect(method = "appendGuideTooltip", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonlib/lang/LangBuilder;forGoggles(Ljava/util/List;)V", ordinal = 2))
    private void fixUnexpectedIndents(LangBuilder itemName, List<? super MutableComponent> tooltip) {
        itemName.addTo(tooltip);
    }

}
