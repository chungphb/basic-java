package com.algorithms;

import java.util.Arrays;

public class TwoSum extends TestBase {
    public static void main(String[] args) {
        testTwoSum();
        testTwoSum2();
    }

    private static void testTwoSum() {
        startTest("2-SUM PROBLEM");

        // Input
        int[] arr = new int[]{8, 7, 2, 5, 3, 1};
        int val = 10;
        System.out.println("INPUT");
        System.out.println("\tArray: " + Arrays.toString(arr));
        System.out.println("\tValue: " + val);

        // Algorithm
        Arrays.sort(arr);
        int start = 0, finish = arr.length - 1;
        while (start < finish) {
            int sum = arr[start] + arr[finish];
            if (sum == val) {
                break;
            } else if (sum > val) {
                --finish;
            } else {
                ++start;
            }
        }

        // Output
        System.out.println("OUTPUT");
        if (start >= finish) {
            System.out.println("\tPair not found");
        } else {
            System.out.println("\tPair found: (" + arr[start] + ", " + arr[finish] + ")");
        }

        endTest();
    }

    private static void testTwoSum2() {
        startTest("2-SUM PROBLEM (2)");

        // Input
        int[] arr = new int[]{8, 7, 2, 5, 3, 1};
        int val = 10;
        System.out.println("INPUT");
        System.out.println("\tArray: " + Arrays.toString(arr));
        System.out.println("\tValue: " + val);

        // Algorithm/Output
        System.out.println("OUTPUT");
        Arrays.sort(arr);
        int start = 0, finish = arr.length - 1;
        while (start < finish) {
            int sum = arr[start] + arr[finish];
            if (sum == val) {
                System.out.println("\tPair found: (" + arr[start] + ", " + arr[finish] + ")");
                ++start;
            } else if (sum > val) {
                --finish;
            } else {
                ++start;
            }
        }

        endTest();
    }
}
