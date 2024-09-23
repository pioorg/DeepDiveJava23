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

package org.przybyl.ddj23.flexibleConstructorBodies;

public class LegalStatementsBeforeSuper extends BaseClass {

    static void main() {
        new LegalStatementsBeforeSuper("let's see if it works");
    }

    LegalStatementsBeforeSuper(String input) {
        // validation of arguments
        assert input != null;

        if (Math.random() < 0.5) {
            // fetching arguments
            input = methodCallingElsewhere();
        }
        // preparation of arguments
        int length = input.length();

        super(input, length);
    }

    static String methodCallingElsewhere() {
        return "not so sure if fetching constructor arguments from the outside is wise idea, even if legal";
    }
}

class BaseClass {
    public BaseClass(String a, int b) {
        System.out.printf("Base class constructor called, a=[%s], b=[%d]%n", a, b);
    }
}
