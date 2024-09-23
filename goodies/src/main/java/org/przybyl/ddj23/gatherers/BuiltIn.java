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

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class BuiltIn {
    void main() {
        folding();
        scanning();
        fixed();
        sliding();
    }

    private void folding() {
        var list = IntStream.range(0, 10)
            .mapToObj(i -> "" + i)
            .gather(Gatherers.fold(() -> "", (string, number) -> string + number))
            .toList();
        System.out.println(list);
    }
    private void scanning() {
        var list = IntStream.range(0, 10)
            .mapToObj(i -> "" + i)
            .gather(Gatherers.scan(() -> "", (string, number) -> string + number))
            .toList();
        System.out.println(list);
    }
    private void fixed() {
        var list = IntStream.range(0, 10)
            .mapToObj(i -> "" + i)
            .gather(Gatherers.windowFixed(3))
            .toList();
        System.out.println(list);
    }
    private void sliding() {
        var list = IntStream.range(0, 10)
            .mapToObj(i -> "" + i)
            .gather(Gatherers.windowSliding(3))
            .toList();
        System.out.println(list);
    }
}
