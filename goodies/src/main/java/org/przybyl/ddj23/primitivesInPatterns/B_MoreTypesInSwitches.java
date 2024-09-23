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


void main() {
    long[] longs = new long[] {0, 1, 2, 3, 99, 123};
    for (long aLong : longs) {
        String msg = switch (aLong) {
//        case 0 -> "zero";
            case 0L -> "zero";
            case long l when l == 1 -> "one";
            case int i when i >= 2 && i < 10 -> "below ten";
            case float f when f < 100 -> "below 100";
            default -> "default";
        };
        println(msg);
    }

    float aFloat = 0.99999999f;
    switch (aFloat) {
        case 1f -> println("one");
//        case 0.99999999f -> println("eight of nines");
        default -> println(aFloat);
    }


    boolean b = true;
    switch (b) {
        case false -> println(false);
        case true -> println(true);
    }
}






