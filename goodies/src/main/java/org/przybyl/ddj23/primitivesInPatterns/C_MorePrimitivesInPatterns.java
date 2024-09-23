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


Number[] gimme() {
    return new Number[]{12, (byte) 12, 222, (short) -12, 12.0, 12.2, 12.2f, 2.0 - 1.1};
}

void main() {

    for (var number : gimme()) {
        handle(number);
    }
}

void handle(Number number) {
    float border = 0.999999999999999999999999999999999999999999999999f;
    String msg = switch (number) {
        case byte b when b > 0 -> "positive byte";
        case byte _ -> "a byte";
        case int _ -> "an integer";
        case double d when d != 0.9 -> "a double (but not 0.9)";
        case float f when f > border -> "floating at least one";
        case float f when f > 1.0 -> "floating at least one, repeated";
        default -> "default";
    };

    println("The message for " + number + " is " + msg);
}

// add generics and composed types



