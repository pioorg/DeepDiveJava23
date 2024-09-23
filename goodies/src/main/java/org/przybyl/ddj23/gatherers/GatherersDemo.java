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

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class GatherersDemo {
    public static void main() {
//        palindromes();
        pairs();
//        lengthFluctuations();
//        concurrentMapping();
    }

    private static void palindromes() {
        var uniqueLengthPalindromes = Source.words()
            .map(String::toLowerCase)
            .gather(Gatherer.ofSequential(Gatherer.Integrator.<Void, String, String>ofGreedy((_, element, downstream) -> {
                downstream.push(element.replaceAll("\\s", ""));
                return true;
            })))
            .filter(Tools::isPalindrome)
            .gather(new UniqueByLengthGatherer())
            .toList();

        uniqueLengthPalindromes.forEach(System.out::println);
    }

    private static void pairs() {
        Source.words()
            .gather(Gatherer.ofSequential(() -> new ArrayList<String>(), Gatherer.Integrator.of((state, element, downstream) -> {
                    if (state.isEmpty()) {
                        state.add(element);
                    } else {
                        downstream.push(new Pair<>(state.getFirst(), element));
                        state.removeFirst();
                    }
                    return true;
                }),
                (state, downstream) -> {
                    if (!state.isEmpty()) {
                        downstream.push(new Pair<>(state.getFirst(), "SINGLE"));
                    }
                }
            ))
            .forEach(System.out::println);
    }

    private static void lengthFluctuations() {
        Source.words()
            .gather(Gatherers.windowSliding(2))
            .filter(l -> Math.abs(l.getFirst().length() - l.getLast().length()) > 1)
            .forEach(System.out::println);
    }

    private static void concurrentMapping() {

        long start = System.nanoTime();
        var events = IntStream.range(0, 100).boxed()
//            .parallel().map(Tools::slowIoDouble)
            .gather(Gatherers.mapConcurrent(100, Tools::slowIoDouble))
            .toArray();
        long stop = System.nanoTime();
        System.out.println("took " + Duration.ofNanos(stop - start) + " to generate " + events);
    }

}
