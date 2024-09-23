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

import java.util.Set;
import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class UniqueByLengthGatherer implements Gatherer<String, Set<Integer>, String> {

    @Override
    public Supplier<Set<Integer>> initializer() {
        return HashSet::new;
    }

    @Override
    public Integrator<Set<Integer>, String, String> integrator() {
        return (state, element, downstream) -> {
            if (!state.contains(element.length())) {
                state.add(element.length());
                downstream.push(element);
//                   } else {
//                       System.out.println("Skipping " + element);
            }
//            return state.size() < 3;
            return true;
        };
    }
}
