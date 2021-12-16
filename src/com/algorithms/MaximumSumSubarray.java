package com.algorithms;

// Description: Given an integer array, find a contiguous subarray within it that has the largest sum.

import java.util.Arrays;

public class MaximumSumSubarray extends TestBase {
    public static void main(String[] args) {
        testMaximumSumSubarray();
        testMaximumSumSubarray2();
    }

    private static void testMaximumSumSubarray() {
        startTest("MAXIMUM SUM SUBARRAY PROBLEM");

        // Input
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("INPUT");
        System.out.println("\tArray: " + Arrays.toString(arr));

        // Algorithm
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            res[i] = Math.max(arr[i], arr[i] + res[i - 1]);
        }

        // Output
        System.out.println("OUTPUT");
        int maxId = 0;
        int maxSum = res[maxId];
        for (int i = 0; i < arr.length; ++i) {
            if (res[i] > maxSum) {
                maxId = i;
                maxSum = res[maxId];
            }
        }
        System.out.println("\tThe maximum sum: " + maxSum);

        endTest();
    }

    private static void testMaximumSumSubarray2() {
        startTest("MAXIMUM SUM SUBARRAY PROBLEM (2)");

        // Input
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("INPUT");
        System.out.println("\tArray: " + Arrays.toString(arr));

        // Algorithm
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            res[i] = Math.max(arr[i], arr[i] + res[i - 1]);
        }

        // Output
        System.out.println("OUTPUT");
        int endId = 0;
        int maxSum = res[endId];
        for (int i = 0; i < arr.length; ++i) {
            if (res[i] > maxSum) {
                endId = i;
                maxSum = res[endId];
            }
        }
        int startId = -1;
        int curSum = maxSum;
        for (int i = endId; i >= 0; --i) {
            if (curSum == arr[i]) {
                startId = i;
                break;
            } else {
                curSum -= arr[i];
            }
        }
        System.out.println("\tThe maximum sum: " + maxSum);
        System.out.println("\tThe maximum sum subarray: " + Arrays.toString(Arrays.copyOfRange(arr, startId, endId + 1)));

        endTest();
    }
}
