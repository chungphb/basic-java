package com.collections.test_list;

import java.util.*;

public class TestList {
    public static void main(String[] args) {
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
