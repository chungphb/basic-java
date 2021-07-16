package com.collections.test_list;

import java.util.*;

public class TestList {
    public static void main(String[] args) {
        { // Test List<T>
            startTest("TEST LIST");

            // add()
            List<Integer> list = new ArrayList<>();
            list.add(4);
            list.add(12);
            list.add(13);
            list.add(25);
            list.add(40);
            System.out.println("List: " + list);

            // get()
            Integer element = list.get(0);
            System.out.println("The 1st element: " + element);

            // remove()
            list.remove(0);
            System.out.println("List (after removing 1st element): " + list);

            // indexOf()
            int index = list.indexOf(13);
            System.out.println("Index of \"13\" element: " + index);

            // set()
            list.set(index, element);
            System.out.println("List (after updating \"13\" element): " + list);

            // sublist()
            List<Integer> subList = list.subList(1, 3);
            System.out.println("Sublist (from 1 to 3): " + subList);

            // iterator()
            System.out.println("Elements: ");
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            list.clear();
            System.out.println("List (after clearing): " + list);

            // isEmpty()
            System.out.println("Is the list empty: " + list.isEmpty());

            endTest();
        }
        { // Test AbstractList<T>
            startTest("TEST ABSTRACT LIST");

            // add()
            AbstractList<String> list = new ArrayList<>();
            list.add("Pham");
            list.add("Huu");
            list.add("Bao");
            list.add("Chung");
            System.out.println("List: " + list);

            // get()
            String element = list.get(1);
            System.out.println("The 2nd element: " + element);

            // remove()
            list.remove(1);
            System.out.println("List (after removing 2nd element): " + list);

            // indexOf()
            int index = list.indexOf("Chung");
            System.out.println("Index of \"Chung\" element: " + index);

            // set()
            list.set(index, element);
            System.out.println("List (after updating \"Chung\" element): " + list);

            // sublist()
            List<String> subList = list.subList(0, 1);
            System.out.println("Sublist (from 0 to 1): " + subList);

            // iterator()
            System.out.println("Elements: ");
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                element = (String) it.next();
                System.out.println(element);
            }

            // clear()
            list.clear();
            System.out.println("List (after clearing): " + list);

            // isEmpty()
            System.out.println("Is the list empty: " + list.isEmpty());

            endTest();
        }
        { // Test AbstractSequentialList<T>
            startTest("TEST ABSTRACT SEQUENTIAL LIST");

            // add()
            AbstractList<String> list = new LinkedList<>();
            list.add("Pham");
            list.add("Le");
            list.add("Thu");
            list.add("Thuy");
            System.out.println("List: " + list);

            // get()
            String element = list.get(1);
            System.out.println("The 2nd element: " + element);

            // remove()
            list.remove(1);
            System.out.println("List (after removing 2nd element): " + list);

            // indexOf()
            int index = list.indexOf("Thuy");
            System.out.println("Index of \"Thuy\" element: " + index);

            // set()
            list.set(index, element);
            System.out.println("List (after updating \"Thuy\" element): " + list);

            // sublist()
            List<String> subList = list.subList(0, 1);
            System.out.println("Sublist (from 0 to 1): " + subList);

            // iterator()
            System.out.println("Elements: ");
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                element = (String) it.next();
                System.out.println(element);
            }

            // clear()
            list.clear();
            System.out.println("List (after clearing): " + list);

            // isEmpty()
            System.out.println("Is the list empty: " + list.isEmpty());

            endTest();
        }
        { // Test ArrayList<T>
            // @AbstractList<T>
        }
        { // Test LinkedList<T>
            // @AbstractSequentialList<T>
        }
        // Note:
        //               ---------------- Collection ----------------
        //              |                                            |
        //              |                                            |
        //      AbstractCollection                                  List
        //              |                                            |
        //              |                                            |
        //               --------------- AbstractList ---------------
        //                                     |
        //                                     |
        //            --------------------------------------------------
        //           |                         |                        |
        //           |                         |                        |
        // AbstractSequentialList          ArrayList                  Vector
        //           |
        //           |
        //       LinkedList
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
