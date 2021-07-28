package com.concurrency.test_high_level_concurrency_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Lover {
    private final String name;

    private final Lock lock = new ReentrantLock();

    public Lover(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean impendingKiss(Lover lover) {
        Boolean myLock = false, yourLock = false;
        try {
            myLock = lock.tryLock();
            yourLock = lover.lock.tryLock();
        } finally {
            if (! (myLock && yourLock)) {
                if (myLock) {
                    lock.unlock();
                }
                if (yourLock) {
                    lover.lock.unlock();
                }
            }
        }
        return myLock && yourLock;
    }

    public synchronized void kiss(Lover lover) {
        if (impendingKiss(lover)) {
            try {
                System.out.format("%s: %s has kissed me!%n", this.name, lover.getName());
                lover.kissBack(this);
            } finally {
                lock.unlock();
                lover.lock.unlock();
            }
        } else {
            System.out.format("%s: %s started to kiss me, but I was already kissing him!%n", this.name, lover.getName());
        }
    }

    public synchronized void kissBack(Lover lover) {
        System.out.format("%s: %s has kissed me back!%n", this.name, lover.getName());
    }
}

class KissLoop implements Runnable {
    private Lover kisser;

    private Lover kissee;

    public KissLoop(Lover kisser, Lover kissee) {
        this.kisser = kisser;
        this.kissee = kissee;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 4; ++i) {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException exception) {}
            kisser.kiss(kissee);
        }
    }
}

class Blur extends RecursiveAction {
    private int[] mSource;

    private int mStart;

    private int mLength;

    private int[] mDestination;

    private int mBlurWidth = 13;

    protected static int sThreshold = 1000;

    public Blur(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; ++index) {
            float red = 0, green = 0, blue = 0;
            for (int offset = -sidePixels; offset <= sidePixels; ++offset) {
                int currentIndex = Math.min(Math.max(index + offset, 0), mSource.length - 1);
                int pixel = mSource[currentIndex];
                red += (float)((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                green += (float)((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                blue += (float)((pixel & 0x000000ff) >> 0) / mBlurWidth;
            }
            int pixel = 0xff000000 | ((((int)red) << 16) | (((int)green) << 8) | (((int)blue) << 0));
            mDestination[index] = pixel;
        }
    }

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }
        int splitPos = mLength / 2;
        invokeAll(new Blur(mSource, mStart, splitPos, mDestination), new Blur(mSource, mStart + splitPos, mLength - splitPos, mDestination));
    }
}

class AtomicCounter {
    private AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();
    }

    public void decrement() {
        value.decrementAndGet();
    }

    public int value() {
        return value.get();
    }
}

public class TestHighLevelConcurrencyObjects {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        { // Lock Objects
            startTest("TEST LOCK");

            final Lover elio = new Lover("Elio");
            final Lover oliver = new Lover("Oliver");
            Thread thread1 = new Thread(new KissLoop(elio, oliver));
            Thread thread2 = new Thread(new KissLoop(oliver, elio));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            endTest();
        }
        { // Executors
            { // Test ExecutorService
                startTest("TEST EXECUTOR SERVICE");

                Runnable runnableTask = () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {}
                    System.out.println("Runnable Task");
                };

                Callable<String> callableTask = () -> {
                    Thread.sleep(1000);
                    System.out.println("Callable Task");
                    return "Task's execution";
                };

                List<Callable<String>> callableTasks = new ArrayList<>();
                callableTasks.add(callableTask);
                callableTasks.add(callableTask);
                callableTasks.add(callableTask);
                callableTasks.add(callableTask);

                ExecutorService executorService = Executors.newFixedThreadPool(12);

                // execute()
                executorService.execute(runnableTask);

                // submit()
                Future<String> future = executorService.submit(callableTask);
                String result = null;
                try {
                    result = future.get();
                } catch (InterruptedException | ExecutionException exception) {}
                System.out.println("Result of submit(): " + result);

                // invokeAny()
                result = executorService.invokeAny(callableTasks);
                System.out.println("Result of invokeAny(): " + result);

                // invokeAll()
                List<Future<String>> futures = executorService.invokeAll(callableTasks);

                // shutdown(), shutdownNow(), awaitTermination()
                executorService.shutdown();
                try {
                    if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException exception) {
                    executorService.shutdownNow();
                }

                endTest();
            }
            { // Test ScheduledExecutorService
                startTest("TEST SCHEDULED EXECUTOR SERVICE");

                Runnable runnableTask = () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {}
                    System.out.println("Runnable Task");
                };

                Callable<String> callableTask = () -> {
                    Thread.sleep(1000);
                    System.out.println("Callable Task");
                    return "Task's execution";
                };

                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

                // schedule()
                Future<String> future = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
                String result = null;
                try {
                    result = future.get();
                } catch (InterruptedException | ExecutionException exception) {}
                System.out.println("Result of schedule(): " + result);

                // scheduleAtFixedRate()
                // executorService.scheduleAtFixedRate(runnableTask, 100, 500, TimeUnit.MILLISECONDS);

                // shutdown(), shutdownNow(), awaitTermination()
                executorService.shutdown();
                try {
                    if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException exception) {
                    executorService.shutdownNow();
                }

                endTest();
            }
            { // Fork/Join
                startTest("TEST FORK/JOIN");

                final int N_PIXELS = 4000;
                Random random = new Random();
                int[] src = new int[N_PIXELS];
                int[] dst = new int[N_PIXELS];
                for (int i = 0; i < src.length; ++i) {
                    int red = random.nextInt(256);
                    int green = random.nextInt(256);
                    int blue = random.nextInt(256);
                    int pixel = 0xff000000 | ((red << 16) | (green << 8) | (blue << 0));
                    src[i] = pixel;
                    dst[i] = 0;
                }
                Blur blur = new Blur(src, 0, src.length, dst);

                // invoke()
                ForkJoinPool pool = new ForkJoinPool();
                pool.invoke(blur);

                for (int i = 0; i < N_PIXELS / 100; ++i) {
                    int pixel, red, green, blue;
                    pixel = src[i];
                    red = (pixel & 0x00ff0000) >> 16;
                    green = (pixel & 0x0000ff00) >> 8;
                    blue = (pixel & 0x000000ff) >> 0;
                    System.out.println("Source: (" + red + ", " + green + ", " + blue + ")");
                    pixel = dst[i];
                    red = (pixel & 0x00ff0000) >> 16;
                    green = (pixel & 0x0000ff00) >> 8;
                    blue = (pixel & 0x000000ff) >> 0;
                    System.out.println("Destination: (" + red + ", " + green + ", " + blue + ")");
                }

                // shutdown(), shutdownNow(), awaitTermination()
                pool.shutdown();
                try {
                    if (!pool.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                        pool.shutdownNow();
                    }
                } catch (InterruptedException exception) {
                    pool.shutdownNow();
                }

                endTest();
            }
        }
        { // Concurrent Collections

        }
        { // Atomic Variables
            startTest("TEST ATOMIC TYPES");

            AtomicCounter counter = new AtomicCounter();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; ++i) {
                        counter.increment();
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 500; ++i) {
                        counter.decrement();
                    }
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("Counter value: " + counter.value());

            endTest();
        }
        { // Concurrent Random Numbers
            startTest("TEST THREAD LOCAL RANDOM");

            Callable<Void> task = () -> {
                int random = ThreadLocalRandom.current().nextInt(0, 255);
                System.out.format("Thread %s: %d%n", Thread.currentThread().getName(), random);
                return null;
            };
            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < 25; ++i) {
                tasks.add(task);
            }
            ExecutorService executorService = Executors.newFixedThreadPool(40);
            executorService.invokeAll(tasks);
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException exception) {
                executorService.shutdownNow();
            }

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
