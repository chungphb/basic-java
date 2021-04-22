package com.basics.test_operators;

public class TestOperators {
    public static void main(String[] args) {
        { // Test arithmetic operators
            System.out.println("25 + 10 = " + (25 + 10));
            System.out.println("25 - 10 = " + (25 - 10));
            System.out.println("25 * 10 = " + (25 * 10));
            System.out.println("25 / 10 = " + (25 / 10));
            System.out.println("25 % 10 = " + (25 % 10));

            System.out.println("25.00 + 10.00 = " + (25.00 + 10.00));
            System.out.println("25.00 - 10.00 = " + (25.00 - 10.00));
            System.out.println("25.00 * 10.00 = " + (25.00 * 10.00));
            System.out.println("25.00 / 10.00 = " + (25.00 / 10.00));
        }
        { // Test unary operators
            int val = 4;
            System.out.println("-" + val + " = " +      ( -val ));
            System.out.println("+" + val + " = " +      ( +val ));
            System.out.println("++" + val + " = " +     ( ++val ));
            System.out.println(val + "++ = " +          ( val++ ));
            System.out.println("--" + val + " = " +     ( --val ));
            System.out.println(val + "-- = " +          ( val-- ));

            boolean val2 = true;
            System.out.println("!" + val2 + " = " +     ( !val2 ));
        }
        { // Test compound statements
            int val;

            val = 25;
            val += 10;
            System.out.println("25 += 10 -> " + val);

            val = 25;
            val -= 10;
            System.out.println("25 -= 10 -> " + val);
            
            val = 25;
            val *= 10;
            System.out.println("25 *= 10 -> " + val);

            val = 25;
            val /= 10;
            System.out.println("25 /= 10 -> " + val);

            val = 25;
            val %= 10;
            System.out.println("25 %= 10 -> " + val);
        }
        { // Test repational operators
            System.out.println("25 != 10 = " + (25 != 10));
            System.out.println("25 == 10 = " + (25 == 10));
            System.out.println("25 >= 10 = " + (25 >= 10));
            System.out.println("25 <= 10 = " + (25 <= 10));
            System.out.println("25 > 10 = " + (25 > 10));
            System.out.println("25 < 10 = " + (25 < 10));
        }
        { // Test logical operators
            System.out.println("true && false = " + (true && false));
            System.out.println("true || false = " + (true || false));
        }
        { // Test ternary operator
            System.out.println("true? \"TRUE\" : \"FALSE\" = " + (true ? "TRUE" : "FALSE"));
        }
        { // Test bitwise operators
            int val1 = 25;
            int val2 = 10;
            System.out.println(toBinaryString(val1) + " & " + toBinaryString(val2) + " = " + toBinaryString(val1 & val2));
            System.out.println(toBinaryString(val1) + " | " + toBinaryString(val2) + " = " + toBinaryString(val1 | val2));
            System.out.println(toBinaryString(val1) + " ^ " + toBinaryString(val2) + " = " + toBinaryString(val1 ^ val2));

            int val = 4;
            System.out.println("~" + toBinaryString(val) + " = " + toBinaryString(~val));
        }
        { // Test shift operators
            int val = -4;
            System.out.println(val + " << 2 = " + (val << 2));
            System.out.println(val + " >> 2 = " + (val >> 2));
            System.out.println(val + " >>> 2 = " + (val >>> 2));
        }
        { // Test instanceof operators
            class Shape {}
            class Triangle extends Shape {}

            Shape shape = new Shape();
            Triangle triangle = new Triangle();
            System.out.println("shape is an instance of Shape: " + (shape instanceof Shape));
            System.out.println("shape is an instance of Triangle: " + (shape instanceof Triangle));
            System.out.println("triangle is an instance of Shape: " + (triangle instanceof Shape));
            System.out.println("triangle is an instance of Triangle: " + (triangle instanceof Triangle));
        }
    }

    static String toBinaryString(int val) {
        return String.format("%32s", Integer.toBinaryString(val)).replace(' ', '0');
    }
}