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
package org.przybyl.ddj23;

import module java.base;
import static java.io.IO.*;

/// This is a simple `App`, that's importing the module [java.base/]
/// It relies also on [Location], where _preposition_ and *reference* are wrapped into:
/// * [List#getFirst()]
/// * [`getLast`][List#getLast()]
// try tossing an empty line
public class App {
    void main() {
        var location = Location.randomBeforeStatement();
        var list = List.of(location.preposition(), location.reference());
        print("Statements ");
        print(list.getFirst());

        print(list.getLast());
        new FlexibleConstructorBodies(" are so cool!");
    }
}

///  The purpose of this class is very simple: just to do stuff before `super()` in [FlexibleConstructorBodies#FlexibleConstructorBodies(String)]
class FlexibleConstructorBodies {
    FlexibleConstructorBodies(String message) {
        System.out.println(message);
        assert message != null;
        super();
    }
}

record Location(String preposition, String reference) {
    static Random rand = new Random();

    /// a very dummy method
    /// @return _before super()_ or _before this()_
    static Location randomBeforeStatement() {
        String reference = switch (rand.nextInt(2)) {
            case int i when i == 0 -> "super()";
            case byte b when b == 1 -> "this()";
            default -> throw new IllegalStateException("this is nuts");
        };
        return new Location("before", reference);
    }
}
