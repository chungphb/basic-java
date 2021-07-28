package com.concurrency.test_thread_objects;

class SampleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Sample runnable!");
    }
}

class SampleThread extends Thread {
    @Override
    public void run() {
        System.out.println("Sample thread!");
    }
}

class JoiningThread extends Thread {
    public long id;

    public JoiningThread(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Hello from thread " + id);
        for (int i = 0; i < 4; ++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("Thread " + id + ": " + i);
        }
    }
}

class ExampleThread {
    static void log(String msg) {
        String currentThread = Thread.currentThread().getName();
        System.out.format("%s: %s%n", currentThread, msg);
    }

    private static class MessageLoop implements Runnable {
        @Override
        public void run() {
            String[] stages = {"Denial", "Anger", "Bargaining", "Depression", "Acceptance"};
            try {
                for (String stage : stages) {
                    Thread.sleep(1000);
                    log(stage);
                }
            } catch (InterruptedException exception) {
                log("Interrupted");
            }
        }
    }

    public static void run() throws InterruptedException {
        long duration = 15 * 1000;

        log("Starting thread...");
        long startTime = System.currentTimeMillis();
        Thread thread = new Thread(new MessageLoop());
        thread.start();

        log("Waiting for thread to finish...");
        while (thread.isAlive()) {
            log("Still waiting...");
            thread.join(1000);
            if (((System.currentTimeMillis() - startTime) > duration) && thread.isAlive()) {
                log("Time is up!");
                thread.interrupt();
                thread.join();
            }
        }
        log("Finishing thread...");
    }
}

public class TestThreadObjects {
    public static void main(String[] args) throws InterruptedException {
        { // Defining and Starting a Thread
            startTest("TEST THREAD");

            (new Thread(new SampleRunnable())).start();
            (new SampleThread()).start();

            endTest();
        }
        { // Pausing Execution with Sleep
            startTest("TEST SLEEP");

            for (int i = 0; i < 4; ++i) {
                Thread.sleep(1000);
                System.out.println(i);
            }

            endTest();
        }
        { // Interrupts
            startTest("TEST INTERRUPT");

            for (int i = 4; i < 8; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    return;
                }
                System.out.println(i);
            }

            for (int i = 8; i < 12; ++i) {
                System.out.println(i);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }

            endTest();
        }
        { // Joins
            startTest("TEST JOIN");

            JoiningThread thread1 = new JoiningThread(1);
            JoiningThread thread2 = new JoiningThread(2);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            JoiningThread thread3 = new JoiningThread(3);
            JoiningThread thread4 = new JoiningThread(4);
            thread3.start();
            thread3.join();
            thread4.start();
            thread4.join();

            endTest();
        }
        { // The Example Thread
            startTest("TEST EXAMPLE");

            ExampleThread.run();

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
