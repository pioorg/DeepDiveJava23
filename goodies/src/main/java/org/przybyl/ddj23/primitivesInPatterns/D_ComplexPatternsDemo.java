/*
 *  Copyright (C) 2023 Piotr Przybył
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

private final static Random random = new Random();

record Wrapper(double wrapped) {
}

record Complex(double real, double imaginary) {
}

void main() {

    var w = new Wrapper(2.0);
//        var w = new Wrapper(2.1);

    if (w instanceof Wrapper(int wrapped)) {
        println("yoo hoo! We wrapped an int! " + wrapped);
    } else {
        println("oops! It was still a double! " + w.wrapped());
    }

    Complex something = gimmeSomething();
    switch (something) {
        case Complex(int _, int _) -> println("both ints");
        case Complex(double _, int _) -> println("first double, second int");
        case Complex(double _, long _) -> println("first double, second long");
        case Complex(byte _, _) -> println("first byte, second whatever");
        case Complex(double _, double _) -> println("both doubles");
        default -> println("I'm done here");
    }
    println(something);
//    println(something.real().getClass());
//    println(something.imaginary().getClass());

}

public static Complex gimmeSomething() {
    return switch (random.nextInt(5)) {
        case 0 -> new Complex(0, 0);
        case 1 -> new Complex(0.5, 0.5);
        case 2 -> new Complex(1.2, 23);
        case 3 -> new Complex(2, 3.333);
        case 4 -> new Complex(2, (long) Integer.MAX_VALUE + 1);
        default -> throw new IllegalArgumentException();
    };
}

