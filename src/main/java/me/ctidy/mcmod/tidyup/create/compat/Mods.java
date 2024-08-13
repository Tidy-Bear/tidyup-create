/*
 * Copyright (c) 2024, Tidy-Bear.
 *
 * This file is part of "Tidy UP: Create".
 *
 * "Tidy UP: Create" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Tidy UP: Create" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Tidy UP: Create".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.tidyup.create.compat;

import com.simibubi.create.foundation.utility.Lang;

/**
 * Mods
 *
 * @author Tidy-Bear
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
