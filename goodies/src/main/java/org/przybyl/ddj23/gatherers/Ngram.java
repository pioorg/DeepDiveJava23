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

import java.util.ArrayList;
import java.util.stream.Gatherer;

public class Ngram {
    void main() {
        String text = "Quick Fox";
        // [ Q, Qu, u, ui, i, ic, c, ck, k, "k ", " ", " F", F, Fo, o, ox, x ]

        text.chars()
            .mapToObj(c -> String.valueOf((char) c))
            .gather(Gatherer.ofSequential(() -> new ArrayList<String>(),
                (state, element, downstream) -> {
                if (state.isEmpty()) {
                    state.add(element);
                    downstream.push(element);
                } else {
                    downstream.push(state.getFirst() + element);
                    downstream.push(element);
                    state.removeFirst();
                    state.add(element);
                }
                return true;
            }))
            .forEach(System.out::println);
    }


}
