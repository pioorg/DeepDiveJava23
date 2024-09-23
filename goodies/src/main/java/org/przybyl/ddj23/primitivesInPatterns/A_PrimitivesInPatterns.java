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

static Random rand = new Random();

int rollZeCube() {
    return rand.nextInt(120);
//    return 0;
}

void main() {
    var input = rollZeCube();

    double number = switch (input) {
        case 0 -> 0;
        case 1 -> -1;
        case 2 -> 12.0;
        case 3 -> 212.0;
        case 4 -> 12.2;
        case 5 -> 12.2f;
        case 6 -> 17.0-13.0f;
        case 7 -> 2.0-1.1;
        default -> 12;
    };

//    float number = 12.3f;
//    float number = 1.00001f;
//    byte number = -12;
//    float number = 0.999999999999999999999999f;


    String msg = switch (number) {
        case byte b when b > 0 -> "positive byte";
        case byte _ -> "a byte";
        case int _ -> "an integer";
        case double d when d != 0.9 -> "a double (but not 0.9)";
        case float f when f > 0.999999999999999999999999f -> "floating at least one";
        case float f when f > 1.0 -> "floating at least one, repeated";
        default -> "default";
    };

    println("The message for " + number + " is " + msg);

}



