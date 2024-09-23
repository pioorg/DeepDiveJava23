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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.Stream;


class EneDue {
    void main() {
//        withCollect();
//        withCollector();
//        withCollectorAndUp();
        withGathererAndUp();
//        withGatherer();
    }

    static Stream<String> eneStream() {
        return Stream.of("""
            Ene due rike fake \
            torba borba ósme smake \
            eus deus kosmateus \
            i morele baks"""
            .split(" "));
    }

    private static void withCollect() {
        Set<String> uniqueLengthWords = eneStream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(
                        String::length,           // Key mapper: use word length as key
                        word -> word,             // Value mapper: use the word itself as value
                        (existing, _) -> existing // Merge function: keep existing word
                    ),
                    map -> new HashSet<>(map.values()) // Finisher: convert map values to set
                )
            );

        uniqueLengthWords.forEach(System.out::println);
    }

    private static void withCollector() {
        Collector<String, HashMap<Integer, String>, Set<String>> theCollector = uniqueLengthCollector();
        Set<String> uniqueLengthWords = eneStream().collect(theCollector);

        uniqueLengthWords.forEach(System.out::println);
    }


    private static void withCollectorAndUp() {
        Set<String> uniqueLengthWords = eneStream()
            .collect(Collectors.collectingAndThen(
                uniqueLengthCollector(),          // Apply the provided collector
                set -> set.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toSet()) // Convert collected words to uppercase
            ));
        uniqueLengthWords.forEach(System.out::println);
    }

    private static void withGathererAndUp() {
        Set<String> uniqueLengthWords = eneStream()
            .gather(new UniqueByLengthGatherer())
            .map(String::toUpperCase)
            .collect(Collectors.toUnmodifiableSet());
        uniqueLengthWords.forEach(System.out::println);
    }

    private static void withGatherer() {
        Gatherer.Integrator<Set<Integer>, String, String> integrator =
            (state, element, downstream) -> {
            if (!state.contains(element.length())) {
                state.add(element.length());
                downstream.push(element);
            }
            return true;
        };
        Set<String> uniqueLengthWords = eneStream()
            .gather(Gatherer.ofSequential(HashSet::new, integrator))
            .collect(Collectors.toUnmodifiableSet());
        uniqueLengthWords.forEach(System.out::println);
    }


    private static Collector<String, HashMap<Integer, String>, Set<String>> uniqueLengthCollector() {
        return Collector.of(
            HashMap::new,                                        // Supplier: create a new HashMap
            (map, word) -> map.putIfAbsent(word.length(), word), // Accumulator: add word if length is not already present
            (map1, map2) -> {                                    // Combiner: merge two maps
                map2.forEach(map1::putIfAbsent);
                return map1;
            },
            map -> new HashSet<>(map.values())                   // Finisher: convert map values to set
        );
    }
}


