package me.ctidy.mcmod.create.tidyspatches.utils;

import joptsimple.internal.Strings;
import net.minecraft.client.gui.Font;
import net.minecraft.util.Mth;

/**
 * SpaceIndentsUtil
 *
 * @author ctidy
 * @since 2024/5/2
 */
public class SpaceIndentsUtil {

    public static final int DEFAULT_SPACE_WIDTH = 4;

    private static int SPACE_WIDTH = -1;

    public static int spaceWidth(Font font) {
        return SPACE_WIDTH == -1 ?
                SPACE_WIDTH = font.width(" ")
                : SPACE_WIDTH;
    }

    public static void onReload() {
        SPACE_WIDTH = -1;
    }

    public static boolean isDefaultAdaptedToFont(Font font) {
        return DEFAULT_SPACE_WIDTH == spaceWidth(font);
    }

    public static String indentsTextAdaptedToFont(Font font, int defaultIndents) {
        int indents = isDefaultAdaptedToFont(font) ?
                defaultIndents
                : Mth.ceil(((float) DEFAULT_SPACE_WIDTH) * defaultIndents / spaceWidth(font));
        return Strings.repeat(' ', indents);
    }

    private SpaceIndentsUtil() { }

}
