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
    int i = 142;
    if (i >= Byte.MIN_VALUE && i <= Byte.MAX_VALUE) {
        println("Can use " + i + " as byte value");
        byte b = (byte) i;
        println(b);
    } else {
        println("Cannot use " + i + " as byte value");
    }

    println("Because if I don't check, it's suddenly " + (byte) i);


    // and now!

    if (i instanceof byte b) {
        println("Can use " + b + " as byte value");
    } else {
        println("Cannot use " + i + " as byte value");
    }

    switch (i) {
        case byte b -> println("Can use " + b + " as byte value");
        case int j -> println("Cannot use " + j + " as byte value");
    }

    switch (i instanceof byte) {
        case true -> println("Can use " + i + " as byte value");
        case false -> println("Cannot use " + i + " as byte value");
    }

    long aLong = 42L;
    String humanValue = switch (aLong) {
        case 0L -> "zero";
        case long l when l < 0 -> "negative";
        case long l when l < 10 -> "one digit";
        case long l when l < 100 -> "below 100";
        case long l -> "unexpected value of " + l;
    };

    println("Our long is " + humanValue);

    record Wrapper(double wrapped) {
    }
    switch (new Wrapper(142.2)) {
        case Wrapper(byte b) -> println("Can use wrapped " + b + " as byte value");
        case Wrapper(int j) -> println("Can use wrapped " + j + " as int value");
        case Wrapper(double d) -> println("Can use wrapped " + d + " as double value");
    }

}
