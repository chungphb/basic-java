package com.algorithms;

// Description: The Longest Common Subsequence (LCS) problem is finding the longest subsequence present in given two sequences
// in the same order, i.e., find the longest sequence which can be obtained from the first original sequence by deleting some
// items and from the second original sequence by deleting other items.

// NOte: Optimal substructure

import java.util.*;

public class LongestCommonSubsequence extends TestBase {
    public static void main(String[] args) {
        testLongestCommonSubsequence();
        testLongestCommonSubsequence2();
    }

    private static void testLongestCommonSubsequence() {
        startTest("LONG COMMON SUBSEQUENCE PROBLEM");

        // Input
        char[] arr1 = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] arr2 = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};
        System.out.println("INPUT");
        System.out.println("\tSequence 1: " + Arrays.toString(arr1));
        System.out.println("\tSequence 2: " + Arrays.toString(arr2));

        // Algorithm
        int[][] res = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i < arr1.length; ++i) {
            for (int j = 0; j < arr2.length; ++j) {
                if (arr1[i] == arr2[j]) {
                    res[i + 1][j + 1] = res[i][j] + 1;
                } else {
                    res[i + 1][j + 1] = Math.max(res[i + 1][j], res[i][j + 1]);
                }
            }
        }

        // Output
        System.out.println("OUTPUT");
        System.out.println("\tThe length of the LCS: " + res[arr1.length][arr2.length]);

        endTest();
    }

    private static void testLongestCommonSubsequence2() {
        startTest("LONG COMMON SUBSEQUENCE PROBLEM (2)");

        // Input
        char[] arr1 = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] arr2 = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};
        System.out.println("INPUT");
        System.out.println("\tSequence 1: " + Arrays.toString(arr1));
        System.out.println("\tSequence 2: " + Arrays.toString(arr2));

        // Algorithm
        int[][] res = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i < arr1.length; ++i) {
            for (int j = 0; j < arr2.length; ++j) {
                if (arr1[i] == arr2[j]) {
                    res[i + 1][j + 1] = res[i][j] + 1;
                } else {
                    res[i + 1][j + 1] = Math.max(res[i + 1][j], res[i][j + 1]);
                }
            }
        }

        // Output
        System.out.println("OUTPUT");
        System.out.println("\tThe length of the LCS: " + res[arr1.length][arr2.length]);

        int i = arr1.length, j = arr2.length;
        List<Character> lcs = new ArrayList<>();
        while (i != 0 && j != 0) {
            if (res[i][j] == res[i - 1][j - 1] + 1) {
                lcs.add(arr1[i - 1]);
                --i;
                --j;
            } else if (res[i][j] == res[i][j - 1]) {
                --j;
            } else if (res[i][j] == res[i - 1][j]) {
                --i;
            }
        }
        System.out.println("\tThe LCS: " + lcs);

        endTest();
    }
}
