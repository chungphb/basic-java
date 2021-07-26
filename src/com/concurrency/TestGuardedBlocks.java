package com.concurrency;

import java.util.Random;

class Data {
    private String message;

    private boolean empty = true;

    public synchronized String get() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException exception) {}
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException exception) {}
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Producer implements Runnable {
    private Data data;

    public Producer(Data data) {
        this.data = data;
    }

    public void run() {
        Random random = new Random();
        String[] stages = {"Denial", "Anger", "Bargaining", "Depression", "Acceptance"};
        for (String stage : stages) {
            data.put(stage);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException exception) {}
        }
        data.put("DONE");
    }
}

class Consumer implements Runnable {
    private Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    public void run() {
        Random random = new Random();
        String[] stages = {"Denial", "Anger", "Bargaining", "Depression", "Acceptance"};
        for (String message = data.get(); !message.equals("DONE"); message = data.get()) {
            System.out.format("Received: %s%n", message);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException exception) {}
        }
    }
}

public class TestGuardedBlocks {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        (new Thread(new Producer(data))).start();
        (new Thread(new Consumer(data))).start();
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
