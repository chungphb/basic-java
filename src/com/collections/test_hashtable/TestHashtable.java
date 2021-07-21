package com.collections.test_hashtable;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class TestHashtable {
    public static void main(String[] args) {
        { // Test Hashtable<K, V>
            startTest("TEST HASHTABLE");

            Hashtable<Integer, String> hashtable = new Hashtable<>();

            // put()
            hashtable.put(4, "four");
            hashtable.put(12, "twelve");
            hashtable.put(13, "thirteen");
            hashtable.put(25, "twenty-five");
            hashtable.put(40, "forty");
            System.out.println("Hashtable: " + hashtable);

            // get()
            System.out.println("Value of key \"25\": " + hashtable.get(25));

            // keySet()
            System.out.println("Key set: " + hashtable.keySet());

            // values()
            System.out.println("Value set: " + hashtable.values());

            // remove()
            hashtable.remove(25);
            System.out.println("Hashtable (after removing key \"25\"): " + hashtable);

            // entrySet()
            System.out.println("Elements: ");
            for (Map.Entry<Integer, String> entry : hashtable.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // keys()
            System.out.println("Keys: ");
            Enumeration<Integer> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                System.out.println(keys.nextElement());
            }

            // elements()
            System.out.println("Values: ");
            Enumeration<String> values = hashtable.elements();
            while (values.hasMoreElements()) {
                System.out.println(values.nextElement());
            }

            // clear()
            hashtable.clear();
            System.out.println("Hashtable (after clearing): " + hashtable);

            // isEmpty()
            System.out.println("Is the hashtable empty: " + hashtable.isEmpty());

            endTest();
        }
        // Note:
        // 1. Hashtable is similar to HashMap, but it is synchronized.
        // 2. While HashMap doesn't provide any Enumeration, Hashtable provides not fail-fast Enumeration.
        // 3. Hashtable is essentially an array of buckets which stores key/value pairs in them. It used the hashCode()
        // method to determine which bucket the key/value pair belongs. When two unequal Objects have the same hashcode,
        // it is called a collision. To resolve collisions, hashtable uses an array of lists. The pairs mapped to a
        // bucket are stored in a list while the list reference is stored in the bucket.
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
