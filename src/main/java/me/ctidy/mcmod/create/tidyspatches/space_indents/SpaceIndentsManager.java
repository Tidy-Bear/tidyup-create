package me.ctidy.mcmod.create.tidyspatches.space_indents;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;

/**
 * SpaceIndentsManager
 *
 * @author ctidy
 * @since 2024/5/2
 */
public final class SpaceIndentsManager {

    public static final int STANDARD_WIDTH = 4;

    private static final MutableComponent SINGLE_SPACE_COMPONENT = Component.literal(" ");

    public static String scaledSpaceIndents(int standardIndents) {
        return scaledSpaceIndents(standardIndents, Style.EMPTY);
    }

    public static String scaledSpaceIndents(int standardIndents, Style style) {
        float width = Minecraft.getInstance().font.width(SINGLE_SPACE_COMPONENT.setStyle(style));
        int scaledIndents = STANDARD_WIDTH == width ?
                standardIndents
                : Mth.ceil(STANDARD_WIDTH * standardIndents / width);
        return Strings.repeat(' ', scaledIndents);
    }

    private SpaceIndentsManager() { }

}
