package com.collections.test_vector;

import java.util.Iterator;
import java.util.Vector;

public class TestVector {
    public static void main(String[] args) {
        { // Test Vector<T>
            startTest("TEST VECTOR");

            Vector<Integer> vector = new Vector<>();

            // add()
            vector.add(0);
            vector.add(1);
            vector.add(4);
            vector.add(12);
            vector.add(13);
            vector.add(25);
            System.out.println("Vector: " + vector);

            // set()
            vector.set(1, 4);
            System.out.println("Vector (after setting 2nd element to \"4\"): " + vector);

            // firstElement(), lastElement(), get()
            System.out.println("First element: " + vector.firstElement());
            System.out.println("Last element: " + vector.lastElement());
            System.out.println("4th element: " + vector.get(3));

            // contains(), indexOf(), lastIndexOf()
            if (vector.contains(4)) {
                System.out.println("Index of \"4\": " + vector.indexOf(4));
                System.out.println("Last index of \"4\": " + vector.lastIndexOf(4));
            }

            // subList()
            System.out.println("Sublist (from 3rd to 5th): " + vector.subList(2, 4));

            // remove()
            vector.remove(new Integer(0));
            System.out.println("Vector (after removing \"0\"): " + vector);

            vector.remove(1);
            System.out.println("Vector (after removing 2nd element): " + vector);

            // iterator()
            System.out.println("Elements: ");
            Iterator<Integer> it = vector.iterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            vector.clear();
            System.out.println("Vector (after clearing): " + vector);

            // isEmpty()
            System.out.println("Is the vector empty: " + vector.isEmpty());

            endTest();
        }
        // Note:
        //                           Collection
        //                               |
        //                               |
        //                              List
        //                               |
        //                               |
        //                             Vector
        //                               |
        //                               |
        //                             Stack
        // 1. Vector implements a dynamic array that means it can grow and shrink as required.
        // 2. Vector is similar to ArrayList, but it is synchronized and has some other legacy methods.
        // 3. Vector maintains an insertion order like an ArrayList but it is rarely used in a non-thread environment.
        // 4. The Iterators returned by the Vector class are fail-fast.
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
