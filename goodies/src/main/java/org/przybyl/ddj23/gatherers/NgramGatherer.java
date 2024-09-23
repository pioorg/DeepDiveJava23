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


import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class NgramGatherer implements Gatherer<String, StringBuilder, String> {

    private final int minGram;
    private final int maxGram;


    public NgramGatherer(int minGram, int maxGram) {
        if (minGram > maxGram || minGram < 1) {
            throw new IllegalArgumentException("Invalid minGram and maxGram values");
        }

        if (maxGram - minGram > 1) {
            throw new IllegalArgumentException("The difference between minGram and maxGram values can't exceed 1");
        }
        this.minGram = minGram;
        this.maxGram = maxGram;

    }

    @Override
    public Supplier<StringBuilder> initializer() {
        return StringBuilder::new;
    }

    @Override
    public Integrator<StringBuilder, String, String> integrator() {
        return Integrator.of((state, element, downstream) -> {

            state.append(element);

            if (state.length() >= minGram) {
                downstream.push(state.toString());
            }

            if (state.length() == maxGram) {
                state.deleteCharAt(0);
                if (state.length() == minGram) {
                    downstream.push(state.toString());
                }
            }

            return true; // Continue processing
        });
    }

    @Override
    public BiConsumer<StringBuilder, Downstream<? super String>> finisher() {
        return (_, _) -> {
            // No additional action needed in finisher for this use case
        };
    }
}