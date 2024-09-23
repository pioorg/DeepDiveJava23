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
    protected String anotherField;

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

        this.anotherField = "value";
        // this.anotherField = instanceMethod();
        // super.aField = "also a value";

        super(input, length);
    }

    static String methodCallingElsewhere() {
        return "not so sure if fetching constructor arguments from the outside is wise idea, even if legal";
    }

    String instanceMethod() {
        return "this is an instance method";
    }
}

class BaseClass {
    public BaseClass(String a, int b) {
        this.aField = "another value";
//        System.out.println(this.aField);
        super();
        System.out.printf("Base class constructor called, a=[%s], b=[%d]%n", a, b);
        System.out.println(this.aField);
        new Shell().new Core();
    }

    protected String aField;
}

class Shell {
    byte b;
    Shell() {
        b = 17;
    }

    String howdy() {
        return "doin' well";
    }

    class Core {
        char c;

        int answer() {
            return 42;
        }

        Core() {
            System.out.println(Shell.this.b);
            System.out.println(Shell.this.howdy());
            System.out.println(b);
            System.out.println(howdy());
//            System.out.println(c);
//            System.out.println(answer());
            super();
        }
    }
}
