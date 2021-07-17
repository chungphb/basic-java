package com.collections.test_deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class TestDeque {
    public static void main(String[] args) {
        { // Test Deque<T>
            startTest("TEST DEQUE");

            Deque<Integer> deque = new LinkedList<>();

            // add()
            deque.add(4);
            deque.add(12);
            deque.add(13);
            System.out.println("Deque: " + deque);

            // addFirst()
            deque.addFirst(1);
            System.out.println("Deque (after adding first): " + deque);

            // addLast()
            deque.addLast(25);
            System.out.println("Deque (after adding last): " + deque);

            // offer()
            deque.offer(40);
            System.out.println("Deque (after offering): " + deque);

            // offerFirst()
            deque.offerFirst(0);
            System.out.println("Deque (after offering first): " + deque);

            // offerLast()
            deque.offerLast(100);
            System.out.println("Deque (after offering last): " + deque);

            // push()
            deque.push(-1);
            System.out.println("Deque (after pushing): " + deque);

            // element()
            System.out.println("Head of deque (using element()): " + deque.element());

            // getFirst()
            System.out.println("Head of deque (using getFirst()): " + deque.getFirst());

            // getLast()
            System.out.println("Tail of deque (using getLast()): " + deque.getLast());

            // element()
            System.out.println("Head of deque (using peek()): " + deque.element());

            // getFirst()
            System.out.println("Head of deque (using peekFirst()): " + deque.peekFirst());

            // getLast()
            System.out.println("Tail of deque (using peekLast()): " + deque.peekLast());

            // pop()
            System.out.println("Head of deque (using pop()): " + deque.pop());
            System.out.println("Deque (after popping): " + deque);

            // poll()
            System.out.println("New head of deque (using poll()): " + deque.poll());
            System.out.println("Deque (after polling): " + deque);

            // pollFirst()
            System.out.println("New head of deque (using pollFirst()): " + deque.pollFirst());
            System.out.println("Deque (after polling first): " + deque);

            // pollLast()
            System.out.println("Tail of deque (using pollLast()): " + deque.pollLast());
            System.out.println("Deque (after polling last): " + deque);

            // remove()
            deque.remove();
            System.out.println("Deque (after removing): " + deque);

            // removeFirst()
            deque.removeFirst();
            System.out.println("Deque (after removing first): " + deque);

            // removeLast()
            deque.removeLast();
            System.out.println("Deque (after removing last): " + deque);

            // iterator()
            System.out.println("Elements (from head to tail): ");
            Iterator<Integer> it = deque.iterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // descendingIterator()
            System.out.println("Elements (from tail to head): ");
            it = deque.descendingIterator();
            while (it.hasNext()) {
                Integer element = (Integer) it.next();
                System.out.println(element);
            }

            // clear()
            deque.clear();
            System.out.println("Deque (after clearing): " + deque);

            // isEmpty()
            System.out.println("Is the deque empty: " + deque.isEmpty());

            endTest();
        }
        { // Test ArrayDeque<T>
            startTest("TEST ARRAY DEQUE");

            Deque<String> deque = new ArrayDeque<>();

            // add()
            deque.add("and");
            deque.add("you");
            deque.add("are");
            System.out.println("Deque: " + deque);

            // addFirst()
            deque.addFirst("can");
            System.out.println("Deque (after adding first): " + deque);

            // addLast()
            deque.addLast("half");
            System.out.println("Deque (after adding last): " + deque);

            // offer()
            deque.offer("way");
            System.out.println("Deque (after offering): " + deque);

            // offerFirst()
            deque.offerFirst("you");
            System.out.println("Deque (after offering first): " + deque);

            // offerLast()
            deque.offerLast("there");
            System.out.println("Deque (after offering last): " + deque);

            // push()
            deque.push("Believe");
            System.out.println("Deque (after pushing): " + deque);

            // element()
            System.out.println("Head of deque (using element()): " + deque.element());

            // getFirst()
            System.out.println("Head of deque (using getFirst()): " + deque.getFirst());

            // getLast()
            System.out.println("Tail of deque (using getLast()): " + deque.getLast());

            // element()
            System.out.println("Head of deque (using peek()): " + deque.element());

            // getFirst()
            System.out.println("Head of deque (using peekFirst()): " + deque.peekFirst());

            // getLast()
            System.out.println("Tail of deque (using peekLast()): " + deque.peekLast());

            // pop()
            System.out.println("Head of deque (using pop()): " + deque.pop());
            System.out.println("Deque (after popping): " + deque);

            // poll()
            System.out.println("New head of deque (using poll()): " + deque.poll());
            System.out.println("Deque (after polling): " + deque);

            // pollFirst()
            System.out.println("New head of deque (using pollFirst()): " + deque.pollFirst());
            System.out.println("Deque (after polling first): " + deque);

            // pollLast()
            System.out.println("Tail of deque (using pollLast()): " + deque.pollLast());
            System.out.println("Deque (after polling last): " + deque);

            // remove()
            deque.remove();
            System.out.println("Deque (after removing): " + deque);

            // removeFirst()
            deque.removeFirst();
            System.out.println("Deque (after removing first): " + deque);

            // removeLast()
            deque.removeLast();
            System.out.println("Deque (after removing last): " + deque);

            // iterator()
            System.out.println("Elements (from head to tail): ");
            Iterator<String> it = deque.iterator();
            while (it.hasNext()) {
                String element = (String) it.next();
                System.out.println(element);
            }

            // descendingIterator()
            System.out.println("Elements (from tail to head): ");
            it = deque.descendingIterator();
            while (it.hasNext()) {
                String element = (String) it.next();
                System.out.println(element);
            }

            // clear()
            deque.clear();
            System.out.println("Deque (after clearing): " + deque);

            // isEmpty()
            System.out.println("Is the deque empty: " + deque.isEmpty());

            endTest();
        }
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
