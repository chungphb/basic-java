package com.collections.test_queue;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQueue {
    public static void main(String[] args) {
        { // Test Queue<T>
            startTest("TEST QUEUE");

            Queue<Integer> queue = new PriorityQueue<>();

            // add()
            queue.add(4);
            queue.add(12);
            queue.add(13);
            queue.add(25);
            queue.add(40);
            System.out.println("Queue: " + queue);

            // peek()
            System.out.println("Head of queue: " + queue.peek());

            // remove()
            queue.remove();
            System.out.println("Queue (after removing head): " + queue);

            // poll()
            System.out.println("New head of queue: " + queue.poll());
            System.out.println("Queue (after polling): " + queue);

            // clear()
            queue.clear();
            System.out.println("Queue (after clearing): " + queue);

            // isEmpty()
            System.out.println("Is the queue empty: " + queue.isEmpty());

            endTest();
        }
        { // Test AbstractQueue<T>
            startTest("TEST ABSTRACT QUEUE");

            AbstractQueue<Integer> queue = new LinkedBlockingQueue<>();

            // add()
            queue.add(4);
            queue.add(12);
            queue.add(13);
            queue.add(25);
            queue.add(40);
            System.out.println("Queue: " + queue);

            // element()
            System.out.println("Head of queue: " + queue.element());

            // remove()
            queue.remove();
            System.out.println("Queue (after removing head): " + queue);
            System.out.println("New head of queue: " + queue.element());

            // iterator()
            System.out.println("Elements: ");
            Iterator<Integer> it = queue.iterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            queue.clear();
            System.out.println("Queue (after clearing): " + queue);

            // isEmpty()
            System.out.println("Is the queue empty: " + queue.isEmpty());

            endTest();
        }
        // Note:
        //               ---------------- Collection ----------------
        //              |                                            |
        //              |                                            |
        //      AbstractCollection                                 Queue
        //              |                                            |
        //              |                                            |
        //               --------------- AbstractQueue --------------
        //                                     |
        //                                     |
        //                               PriorityQueue
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
