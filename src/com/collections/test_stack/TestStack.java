package com.collections.test_stack;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        { // Test Stack<T>
            startTest("TEST STACK");

            Stack<Integer> stack = new Stack<>();

            // push()
            stack.push(0);
            stack.push(1);
            stack.push(4);
            stack.push(12);
            stack.push(13);
            stack.push(25);
            System.out.println("Stack: " + stack);

            // pop()
            System.out.println("Head of stack: " + stack.pop());
            System.out.println("Stack (after popping): " + stack);

            // peek()
            System.out.println("New head of stack: " + stack.pop());

            // search()
            int element = 4;
            int index = stack.search(element);
            if (index == -1) {
                System.out.println("Element: \"" + element + "\" not found");
            } else {
                System.out.println("Element: \"" + element + "\" is found at index " + index);
            }

            element = 5;
            index = stack.search(element);
            if (index == -1) {
                System.out.println("Element: \"" + element + "\" not found");
            } else {
                System.out.println("Element: \"" + element + "\" is found at index " + index);
            }

            // isEmpty()
            System.out.println("Is the stack empty: " + stack.isEmpty());

            endTest();
        }
        // Note:
        // 1. Stack class is a legacy class and inherits from Vector class.
        // 2. Stack is a thread-safe class and hence involves overhead when we do not need thread safety.
        // 3. ArrayDeque can be used for stack implementation as it is more efficient in a single-threaded environment.
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
