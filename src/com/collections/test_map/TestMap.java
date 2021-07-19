package com.collections.test_map;

import java.util.*;

enum Color {PINK, BLUE, YELLOW, GREEN, WHITE};

public class TestMap {
    public static void main(String[] args) {
        { // Test Map<K, V>
            startTest("TEST MAP");

            Map<Integer, String> map = new HashMap<>();

            // put()
            map.put(4, "Pham");
            map.put(12, "Huu");
            map.put(13, "Bao");
            map.put(25, "Chung");
            System.out.println("Map: " + map);

            // get()
            System.out.println("Value of key \"4\": " + map.get(4));

            // remove()
            map.remove(4);
            System.out.println("Map (after removing key \"4\"): " + map);

            // entrySet()
            System.out.println("Elements: ");
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // clear()
            map.clear();
            System.out.println("Map (after clearing): " + map);

            // isEmpty()
            System.out.println("Is the map empty: " + map.isEmpty());

            endTest();
        }
        { // Test EnumMap<K, V>
            startTest("TEST ENUM MAP");

            EnumMap<Color, String> map = new EnumMap<>(Color.class);

            // put()
            map.put(Color.PINK, "PHAM");
            map.put(Color.BLUE, "Huu");
            map.put(Color.YELLOW, "Bao");
            map.put(Color.GREEN, "Chung");
            System.out.println("Map: " + map);

            // replace()
            map.replace(Color.PINK, "Pham");
            System.out.println("Map (after replacing key \"PINK\"): " + map);

            // get()
            System.out.println("Value of key \"BLUE\": " + map.get(Color.BLUE));

            // keySet()
            System.out.println("Key set: " + map.keySet());

            // values()
            System.out.println("Value set: " + map.values());

            // remove()
            map.remove(Color.GREEN);
            System.out.println("Map (after removing key \"GREEN\"): " + map);

            // entrySet()
            System.out.println("Elements: ");
            for (Map.Entry<Color, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // clear()
            map.clear();
            System.out.println("Map (after clearing): " + map);

            // isEmpty()
            System.out.println("Is the map empty: " + map.isEmpty());

            endTest();
        }
        { // Test HashMap<K, V>
            startTest("TEST HASH MAP");

            HashMap<String, Integer> map = new HashMap<>();

            // put()
            map.put("four", 4);
            map.put("twelve", 12);
            map.put("thirteen", 13);
            map.put("twenty five", 25);
            System.out.println("Map: " + map);

            // containsKey(), get()
            if (map.containsKey("four")) {
                System.out.println("Value of key \"four\": " + map.get("four"));
            }

            // keySet()
            System.out.println("Key set: " + map.keySet());

            // values()
            System.out.println("Value set: " + map.values());

            // remove()
            map.remove("twelve");
            System.out.println("Map (after removing key \"twelve\"): " + map);

            // entrySet()
            System.out.println("Elements: ");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // clear()
            map.clear();
            System.out.println("Map (after clearing): " + map);

            // isEmpty()
            System.out.println("Is the map empty: " + map.isEmpty());

            endTest();
        }
        { // Test SortedMap<K, V>
            startTest("TEST SORTED MAP");

            SortedMap<Integer, String> map = new TreeMap<>();

            // put()
            map.put(4, "You");
            map.put(12, "only");
            map.put(13, "live");
            map.put(25, "once");
            System.out.println("Map: " + map);

            // get()
            System.out.println("Value of key \"12\": " + map.get(12));

            // remove()
            map.remove(12);
            System.out.println("Map (after removing key \"12\"): " + map);

            // entrySet()
            System.out.println("Elements: ");
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // clear()
            map.clear();
            System.out.println("Map (after clearing): " + map);

            // isEmpty()
            System.out.println("Is the map empty: " + map.isEmpty());

            endTest();
        }
        { // Test WeakHashMap<K, V>
            // @HashMap<K, V>
            // Note: An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use.
        }
        { // Test LinkedHashMap<K, V>
            // @HashMap<K, V>
            // Note: It is the same as HashMap with an additional feature that it maintains insertion order.
        }
        { // Test IdentityHashMap<K, V>
            // @IdentityHashMap<K, V>
            // Note: It follows reference equality, instead of using the equals() method it used the == operator.
        }
        { // Test TreeMap<K, V>
            // @SortedMap<K, V>
        }
        { // Test Dictionary
            // @Map<K, V>
            // Note: It is an abstract class, representing a key-value relation and works similar to a map.
        }
        // Note:
        //               ---------------- Collection ----------------
        //              |                                            |
        //              |                                            |
        //      AbstractCollection                                  Map
        //              |                                            |
        //              |                                            |
        //               ---------------- AbstractMap --------------------------------- SortedMap
        //                                     |                                            |
        //                                     |                                            |
        //               ----------------------------------------------------------    NavigableMap
        //              |              |               |             |             |        |
        //              |              |               |             |             |        |
        //           EnumMap      WeakHashMap   IdentityHashMap   HashMap           ---- TreeMap
        //                                                           |
        //                                                           |
        //                                                     LinkedHashMap
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
