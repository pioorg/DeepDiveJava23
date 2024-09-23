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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class NgramGathererTest {

    private static Stream<String> stringToCharStream(String text) {
        return text.chars().mapToObj(c -> String.valueOf((char) c));
    }

    @Test
    public void testGenerateNgrams_Min3Max5() {
        String text = "abc";
        int minGram = 3;
        int maxGram = 5;

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                stringToCharStream(text)
                    .gather(new NgramGatherer(minGram, maxGram))
                    .toList();
            }
        );

        String expectedMessage = "The difference between minGram and maxGram values can't exceed 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid minGram and maxGram values.");
    }

    @Test
    public void testGenerateNgrams_Min3Max3() {
        String text = "abcde";
        int minGram = 3;
        int maxGram = 3;
        List<String> expected = List.of("abc", "bcd", "cde");

        List<String> actual = stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();

        assertEquals(expected, actual, "N-grams with minGram=3 and maxGram=4 should match expected output.");
    }

    @Test
    public void testGenerateNgrams_Min3Max4() {
        String text = "abcde";
        int minGram = 3;
        int maxGram = 4;
        List<String> expected = List.of("abc", "abcd", "bcd", "bcde", "cde");

        List<String> actual = stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();

        assertEquals(expected, actual, "N-grams with minGram=3 and maxGram=4 should match expected output.");
    }

    @Test
    public void testGenerateNgrams_SingleCharacterInput() {
        String text = "a";
        int minGram = 1;
        int maxGram = 2;
        List<String> expected = List.of("a");

        List<String> actual = stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();

        assertEquals(expected, actual, "Single-character input should produce correct n-grams.");
    }


    @Test
    public void testGenerateNgrams_MinGramGreaterThanInputLength() {
        String text = "abc";
        int minGram = 5;
        int maxGram = 5;
        List<String> expected = List.of();

        List<String> actual = stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();

        assertEquals(expected, actual, "No n-grams should be produced when minGram > input length.");
    }

    @Test
    public void testGenerateNgrams_EmptyInput() {
        String text = "";
        int minGram = 1;
        int maxGram = 2;
        List<String> expected = List.of();

        List<String> actual = stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();

        assertEquals(expected, actual, "Empty input should produce an empty list.");
    }

    @Test
    public void testGenerateNgrams_InvalidMinGramMaxGram() {
        String text = "abcde";
        int minGram = 3;
        int maxGram = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringToCharStream(text).gather(new NgramGatherer(minGram, maxGram)).toList();
        });

        String expectedMessage = "Invalid minGram and maxGram values";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid minGram and maxGram values.");
    }
}
