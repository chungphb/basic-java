package com.collections.test_collection;

import java.util.*;

public class TestCollection {
    public static void main(String[] args) {
        { // Test Collection interface
            { // Test List interface
                List<Integer> list1 = new ArrayList<>();
                List<Integer> list2 = new LinkedList<>();
                List<Integer> list3 = new Vector<>();
                Vector<Integer> vector = new Stack<>();
            }
            { // Test Queue interface
                Queue<Integer> queue = new PriorityQueue<>();
                Deque<Integer> deque = new ArrayDeque<>();
            }
            { // Test Set interface
                Set<Integer> set1 = new HashSet<>();
                Set<Integer> set2 = new LinkedHashSet<>();
                SortedSet<Integer> sortedSet = new TreeSet<>();
            }
        }
        { // Test Map interface
            AbstractMap<Integer, String> abstractMap1 = new HashMap<>();
            AbstractMap<Integer, String> abstractMap2 = new LinkedHashMap<>();
            SortedMap<Integer, String> sortedMap = new TreeMap<>();
        }
        // Note: AbstractCollection<T> is used to implement an unmodifiable collection, for which one needs to only
        // extend it and implement the iterator and the size methods only.
        { // Test AbstractCollection<T>
            startTest("TEST ABSTRACT COLLECTION");

            AbstractCollection<Integer> numbers = new ArrayList<>();
            numbers.add(0);
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
            System.out.println("Numbers: " + numbers);

            endTest();
        }
        { // Test iterators
            { // Test Enumeration<T>
                startTest("TEST ENUMERATION");

                Vector<Integer> numbers = new Vector<>();
                for (int i = 0; i < 4; ++i) {
                    numbers.add(i);
                }
                System.out.println("Numbers: " + numbers);
                Enumeration en = numbers.elements();
                System.out.println("Elements:");
                while (en.hasMoreElements()) {
                    int number = (Integer) en.nextElement();
                    System.out.println(number);
                }

                endTest();
            }
            { // Test Iterator<T>
                startTest("TEST ITERATOR");

                TreeSet<Integer> numbers = new TreeSet<>();
                for (int i = 0; i < 4; ++i) {
                    numbers.add(i);
                }
                System.out.println("Numbers: " + numbers);
                Iterator it = numbers.iterator();
                while (it.hasNext()) {
                    int number = (Integer) it.next();
                    if (number % 2 == 1) {
                        System.out.println("Remove element: " + number);
                        it.remove();
                    }
                }
                System.out.println("Result: " + numbers);

                endTest();
            }
            { // Test ListIterator<T>
                startTest("TEST LIST ITERATOR");

                LinkedList<Integer> numbers = new LinkedList<>();
                for (int i = 0; i < 10; ++i) {
                    numbers.add(i);
                }
                System.out.println("Numbers: " + numbers);

                // Forward direction

                ListIterator it = numbers.listIterator();
                while (it.hasNext()) {
                    int number = (Integer) it.next();
                    if (number % 2 == 1) {
                        System.out.println("Remove element: " + number);
                        it.remove();
                    }
                }
                System.out.println("Result: " + numbers);

                // Backward direction

                it = numbers.listIterator();
                while (it.hasPrevious()) {
                    int number = (Integer) it.previous();
                    if (number % 3 == 0) {
                        System.out.println("Remove element: " + number);
                        it.remove();
                    }
                }
                System.out.println("Result: " + numbers);

                // Other methods

                it = numbers.listIterator();
                int index = it.nextIndex();
                it.next();
                System.out.println("Set element at index " + index + " to \"" + 12 + "\"");
                it.set(12);
                System.out.println("Result: " + numbers);
                System.out.println("Add \"13\" to index " + it.nextIndex());
                it.add(13);
                System.out.println("Result: " + numbers);

                endTest();
            }
        }
        // Note:
        // 1. Initially, any iterator reference will point to the index just before the index of the first element.
        // 2. We don't create objects of Enumeration, Iterator and ListIterator because they are interfaces. We use
        // elements(), iterator() and listIterator() to create objects. These methods() have an anonymous inner class
        // that extends respective interfaces and return this class object.
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
