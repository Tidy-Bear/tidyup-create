package me.ctidy.mcmod.create.tidyspatches.compat;

import com.simibubi.create.foundation.utility.Lang;

/**
 * Mods
 *
 * @author ctidy
 * @since 2024/8/7
 */
public enum Mods implements IMod {

    FUNCTIONALSTORAGE,
    SOPHISTICATEDSTORAGE,
    SOPHISTICATEDBACKPACKS;

    private final String id;

    Mods() {
        id = Lang.asId(name());
    }

    @Override
    public String id() {
        return id;
    }

}
