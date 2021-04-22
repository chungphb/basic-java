package com.basics.test_basic_statements;

public class TestBasicStatements {
    public static void main(String[] args) {
        { // Test for
            short sum = 0;
            for (short val = 0; val < 10; ++val) {
                sum += val;
            }
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (for)");

            sum = 0;
            for (short val = 9; val > 0; --val) {
                sum += val;
            }
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (for backward)");            
        }
        { // Test for each
            short sum = 0;
            short[] vals = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            for (short val : vals) {
                sum += val;
            }
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (for each)");
        }
        { // Test while
            short sum = 0;
            short val = 0;
            while (val < 10) {
                sum += val;
                ++val;
            }
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (while)");
        }
        { // Test do while
            short sum = 0;
            short val = 0;
            do {
                sum += val;
                ++val;
            } while (val < 10);
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (do while)");
        }
        { // Test if else if
            int val = 4;
            if (val % 2 != 0) {
                System.out.println(val + " is an odd number");
            } else {
                System.out.println(val + " is an even number");
            }
        }
        { // Test switch
            String day = "Monday";
            switch (day) {
                case "Monday":
                case "Tuesday":
                case "Wednesday":
                case "Thursday":
                case "Friday":
                    System.out.println(day + " is a weekday");
                    break;
                case "Saturday":
                case "Sunday":
                    System.out.println(day + " is a weekend");
                    break;
                default:
                    System.out.println(day + " is not a valid day");
                    break;
            }
        }
        { // Test break, continue
            int sum = 0;
            int val = -10;
            while (true) {
                if (val < 0) {
                    val++;
                    continue;
                } else if (val > 10) {
                    break;
                } else {
                    sum += val;
                    val++;
                }
            }
            System.out.println("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = " + sum + " (mixed)");
        }
    }
}