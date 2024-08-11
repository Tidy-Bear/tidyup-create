package me.ctidy.mcmod.create.tidyspatches.mixin.schematic_better_tooltips;

import com.simibubi.create.content.schematics.SchematicItem;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SchematicItemMixin
 *
 * @author ctidy
 * @since 2024/8/7
 */
@Mixin(value = SchematicItem.class, remap = false)
public abstract class SchematicItemMixin {

    @Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, shift = At.Shift.AFTER, remap = false), remap = true)
    private void appendMoreInfoIntoTooltip(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag, CallbackInfo ci) {
        CompoundTag tag = stack.getTag();
        //noinspection ConstantConditions
        if (tag.contains("Bounds")) {
            ListTag list = tag.getList("Bounds", Tag.TAG_INT);
            String sizeText = list.stream()
                    .map(t -> String.valueOf(((IntTag) t).getAsInt()))
                    .collect(Collectors.joining("×"));
            tooltip.add(Components.literal(sizeText).withStyle(ChatFormatting.GOLD));
        }
    }

}
