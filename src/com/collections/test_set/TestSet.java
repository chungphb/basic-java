package com.collections.test_set;

import java.util.*;

enum Color {PINK, BLUE, YELLOW, GREEN, WHITE};

public class TestSet {
    public static void main(String[] args) {
        { // Test Set<T>
            startTest("TEST SET");

            Set<Integer> set = new HashSet<>();

            // add()
            set.add(4);
            set.add(4);
            set.add(12);
            set.add(12);
            set.add(13);
            set.add(13);
            set.add(25);
            set.add(25);
            System.out.println("Set: " + set);

            // contains()
            System.out.println("Does the set contain \"4\": " + set.contains(4));
            System.out.println("Does the set contain \"5\": " + set.contains(5));

            // remove()
            set.remove(13);
            System.out.println("Set (after removing \"13\"): " + set);

            // iterator()
            System.out.println("Elements: ");
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            set.clear();
            System.out.println("Set (after clearing): " + set);

            // isEmpty()
            System.out.println("Is the set empty: " + set.isEmpty());

            { // Test Set<T> operations
                Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
                Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 3, 5, 7, 9));
                System.out.println("Set 1: " + set1);
                System.out.println("Set 2: " + set2);

                // Union
                Set<Integer> union = new HashSet<>(set1);
                union.addAll(set2);
                System.out.println("Set 1 v Set 2: " + union);

                // Intersection
                Set<Integer> intersection = new HashSet<>(set1);
                intersection.retainAll(set2);
                System.out.println("Set 1 ^ Set 2: " + intersection);

                // Symmetric difference
                Set<Integer> difference = new HashSet<>(set1);
                difference.removeAll(set2);
                System.out.println("Set 1 \\ Set 2: " + difference);
            }

            endTest();
        }
        { // Test AbstractSet<T>
            startTest("TEST ABSTRACT SET");

            AbstractSet<String> set = new TreeSet<>();

            // add()
            set.add("Pham");
            set.add("Pham");
            set.add("Huu");
            set.add("Huu");
            set.add("Bao");
            set.add("Bao");
            set.add("Chung");
            set.add("Chung");
            System.out.println("Set: " + set);

            // contains()
            System.out.println("Does the set contain \"Chung\": " + set.contains("Chung"));
            System.out.println("Does the set contain \"Thuy\": " + set.contains("Thuy"));

            // remove()
            set.remove("Bao");
            System.out.println("Set (after removing \"Bao\"): " + set);

            // iterator()
            System.out.println("Elements: ");
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String element = (String) it.next();
                System.out.println(element);
            }

            // clear()
            set.clear();
            System.out.println("Set (after clearing): " + set);

            // isEmpty()
            System.out.println("Is the set empty: " + set.isEmpty());

            { // Test Set<T> operations
                Set<String> set1 = new HashSet<>(Arrays.asList("Pham", "Huu", "Bao", "Chung"));
                Set<String> set2 = new HashSet<>(Arrays.asList("Pham", "Le", "Thu", "Thuy"));
                System.out.println("Set 1: " + set1);
                System.out.println("Set 2: " + set2);

                // Union
                Set<String> union = new HashSet<>(set1);
                union.addAll(set2);
                System.out.println("Set 1 v Set 2: " + union);

                // Intersection
                Set<String> intersection = new HashSet<>(set1);
                intersection.retainAll(set2);
                System.out.println("Set 1 ^ Set 2: " + intersection);

                // Symmetric difference
                Set<String> difference = new HashSet<>(set1);
                difference.removeAll(set2);
                System.out.println("Set 1 \\ Set 2: " + difference);
            }
            endTest();
        }
        { // Test EnumSet<T>
            startTest("TEST ENUM SET");

            EnumSet<Color> color1, color2, color3, color4;
            color1 = EnumSet.of(Color.PINK, Color.BLUE, Color.YELLOW);
            color2 = EnumSet.complementOf(color1);
            color3 = EnumSet.allOf(Color.class);
            color4 = EnumSet.range(Color.PINK, Color.GREEN);
            System.out.println("Color 1: " + color1);
            System.out.println("Color 2: " + color2);
            System.out.println("Color 3: " + color3);
            System.out.println("Color 4: " + color4);

            endTest();
        }
        { // Test HashSet<T>
            // @Set<T>
        }
        { // Test TreeSet<T>
            startTest("TEST TREE SET");

            NavigableSet<Integer> set = new TreeSet<>();

            // add()
            set.add(0);
            set.add(1);
            set.add(4);
            set.add(12);
            set.add(13);
            set.add(25);
            set.add(40);
            set.add(100);
            System.out.println("Set: " + set);

            // contains()
            System.out.println("Does the set contain \"4\": " + set.contains(4));

            // first()
            System.out.println("First element: " + set.first());

            // last()
            System.out.println("Last element: " + set.last());

            // higher()
            System.out.println("Element just greater than \"12\": " + set.higher(12));

            // lower()
            System.out.println("Element just smaller than \"12\": " + set.lower(12));

            // remove()
            set.remove(100);
            System.out.println("Set (after removing \"100\"): " + set);

            // pollFirst()
            set.pollFirst();
            System.out.println("Set (after polling first): " + set);

            // pollLast()
            set.pollLast();
            System.out.println("Set (after polling last): " + set);

            // iterator()
            System.out.println("Elements: ");
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            set.clear();
            System.out.println("Set (after clearing): " + set);

            // isEmpty()
            System.out.println("Is the set empty: " + set.isEmpty());

            endTest();
        }
        // Note:
        //               ---------------- Collection ----------------
        //              |                                            |
        //              |                                            |
        //      AbstractCollection                                  Set
        //              |                                            |
        //              |                                            |
        //               ---------------- AbstractSet -------------------------------- SortedSet
        //                                     |                                           |
        //                                     |                                           |
        //               --------------------------------------------                 NavigableSet
        //              |                      |                     |                     |
        //              |                      |                     |                     |
        //           EnumSet                HashSet                   ----------------- TreeSet
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
