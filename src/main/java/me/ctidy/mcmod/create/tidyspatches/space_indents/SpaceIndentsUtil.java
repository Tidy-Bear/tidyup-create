package me.ctidy.mcmod.create.tidyspatches.space_indents;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;

/**
 * SpaceIndentsUtil
 *
 * @author ctidy
 * @since 2024/5/2
 */
public final class SpaceIndentsUtil {

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

    public static MutableComponent componentWithScaledSpaceIndents(String rawText) {
        return componentWithScaledSpaceIndents(rawText, Style.EMPTY);
    }

    public static MutableComponent componentWithScaledSpaceIndents(String rawText, Style style) {
        String content = textWithScaledSpaceIndents(rawText);
        if (content.isEmpty()) {
            return Component.empty().withStyle(style);
        }
        return Component.literal(content).withStyle(style);
    }

    public static String textWithScaledSpaceIndents(String rawText) {
        if (rawText == null || rawText.isEmpty()) {
            return "";
        }
        int i = SpaceIndentsUtil.indexOfNonSpace(rawText);
        if (i == 0) {
            return rawText;
        }
        int length = rawText.length();
        if (i < length) {
            return SpaceIndentsUtil.scaledSpaceIndents(i) + rawText.substring(i);
        }
        return SpaceIndentsUtil.scaledSpaceIndents(length);
    }

    public static int indexOfNonSpace(String str) {
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c != ' ') return i;
            i++;
        }
        return i;
    }

    private SpaceIndentsUtil() { }

}
