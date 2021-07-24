package com.concurrency;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

class Counter {
    private int value = 0;

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int value() {
        return value;
    }
}

class CounterRunnable implements Runnable {
    static Counter counter = new Counter();
    private final long id;

    public CounterRunnable(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        if (id % 2 == 0) {
            for (int i = 0; i < 1000; ++i) {
                counter.increment();
            }
        } else {
            for (int i = 0; i < 500; ++i) {
                counter.decrement();
            }
        }
    }
}

class SynchronizedCounter {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public synchronized int value() {
        return value;
    }
}

class SynchronizedCounterRunnable implements Runnable {
    static Counter counter = new Counter();
    private final long id;

    public SynchronizedCounterRunnable(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        if (id % 2 == 0) {
            for (int i = 0; i < 1000; ++i) {
                counter.increment();
            }
        } else {
            for (int i = 0; i < 500; ++i) {
                counter.decrement();
            }
        }
    }
}

class Student {
    static long count;

    static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    private String name;

    public void addName(String name) {
        synchronized (this) {
            this.name = name;
            count++;
        }
        list.add(name);
    }
}

class StudentRunnable implements Runnable {
    static Student student = new Student();
    private final String name;

    public StudentRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        student.addName(name);
    }
}

class Location {
    private long latitude = 0;
    private long longitude = 0;
    private final Object latitudeLock = new Object();
    private final Object longitudeLock = new Object();

    public void increaseLatitude() {
        synchronized (latitudeLock) {
            ++latitude;
        }
    }

    public void increaseLongitude() {
        synchronized (longitudeLock) {
            ++longitude;
        }
    }

    public void print() {
        System.out.println("Location: (" + latitude + "; " + longitude + ")");
    }
}

class LocationRunnable implements Runnable {
    static Location location = new Location();
    private final long id;

    public LocationRunnable(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        if (id % 2 == 0) {
            location.increaseLatitude();
        } else {
            location.increaseLongitude();
        }
    }
}

public class TestSynchronization {
    public static void main(String[] args) throws InterruptedException {
        { // Thread Interference
            startTest("TEST THREAD INTERFERENCE");

            Thread thread1 = new Thread(new CounterRunnable(0));
            Thread thread2 = new Thread(new CounterRunnable(1));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("Value: " + CounterRunnable.counter.value());

            endTest();
        }
        { // Memory Consistency Errors

        }
        { // Synchronized Methods
            startTest("TEST SYNCHRONIZED METHODS");

            Thread thread1 = new Thread(new SynchronizedCounterRunnable(0));
            Thread thread2 = new Thread(new SynchronizedCounterRunnable(1));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("Value: " + SynchronizedCounterRunnable.counter.value());

            endTest();
        }
        { // Intrinsic Locks and Synchronization
            startTest("TEST SYNCHRONIZED STATEMENTS");

            ArrayList<Thread> threads = new ArrayList<>(40);
            for (int i = 0; i < 40; ++i) {
                threads.add(new Thread(new StudentRunnable("Student " + i)));
            }
            for (int i = 0; i < 40; ++i) {
                threads.get(i).start();
            }
            for (int i = 0; i < 40; ++i) {
                threads.get(i).join();
            }
            System.out.println("Student count: " + Student.count);
            System.out.println("Student list: " + Student.list);
            
            threads.clear();

            for (int i = 0; i < 40; ++i) {
                threads.add(new Thread(new LocationRunnable(i)));
            }
            for (int i = 0; i < 40; ++i) {
                threads.get(i).start();
            }
            for (int i = 0; i < 40; ++i) {
                threads.get(i).join();
            }
            LocationRunnable.location.print();

            endTest();
        }
        { // Atomic Access

        }
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
