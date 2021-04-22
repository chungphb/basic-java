class Counter {
    private long value;

    // Constructor

    public Counter(int value) {
        this.value = value;
        System.out.println("Original value: " + this.value);
    }

    // Getters and setters

    public long getValue() {
        return value;
    }

    // Methods

    public void increase() {
        value++;
        System.out.println("After increasing by 1: " + value);
    }

    public void increase(int step) {
        value += step;
        System.out.println("After increasing by " + step + ": " + value + " (int)");
    }

    public void increase(long step) {
        value += step;
        System.out.println("After increasing by " + step + ": " + value + " (long)");
    }

    public void decrease() {
        value--;
        System.out.println("After decreasing by 1: " + value);
    }

    public void decrease(int step) {
        value -= step;
        System.out.println("After decreasing by " + step + ": " + value + " (int)");
    }

    public void decrease(long step) {
        value -= step;
        System.out.println("After decreasing by " + step + ": " + value + " (long)");
    }
}

class Calculator {
    public static int sum(int x, int y) {
        return x + y;
    }

    public static int sum(int x, int y, int z) {
        return x + y + z;
    }
}

public class TestOverloading {
    public static void main(String[] args) {
        { // Test overloading methods
            Counter counter = new Counter(0);
            counter.increase();
            counter.increase(4);
            counter.increase(4L);
        }
        { // Test overloading static methods
            int x = 1, y = 2, z = 3;
            int res;

            res = Calculator.sum(x, y);
            System.out.println(x + " + " + y + " = " + res);

            res = Calculator.sum(x, y, z);
            System.out.println(x + " + " + y + " + " + z + " = " + res);
        }
        { // Test overloading main
            TestOverloading.main("Hello world!");
        }
        // Note: 
        // 1. Java doesn’t allow overloading two methods if they differ only by static keyword
        // 2. Java doesn’t allow user-defined overloaded operators
        // 3. Overloading is an example of compiler time polymorphism while overriding is an example of run time polymorphism
    }

    public static void main(String arg) {
        System.out.println(arg);
    }
}