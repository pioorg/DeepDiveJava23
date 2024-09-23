/*
 *  Copyright (C) 2024 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.przybyl.ddj23.gatherers;

import java.util.stream.Stream;

public interface Source {

    static Stream<String> words() {
        return Stream.of(
            "diamond", "jacket", "race car", "cherry", "dolphin", "coffee", "rocket", "kayak", "flower", "window", "guitar", "music", "cloud", "table", "train", "apple", "silver", "forest", "green", "river", "race", "civic", "noon", "redder", "rotator", "malayalam", "deified", "deleveled", "redivider", "detartrated", "evitative", "rotavator", "tenet", "stats", "banana", "madam", "civic", "refer", "repaper", "rotor", "deified", "level", "radar"
        );
    }

}
