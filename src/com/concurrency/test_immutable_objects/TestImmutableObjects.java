package com.concurrency.test_immutable_objects;

class SynchronizedRGB {
    private int red;

    private int green;

    private int blue;

    private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red, int green, int blue, String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }
}

final class ImmutableRGB {
    final private int red;

    final private int green;

    final private int blue;

    final private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name);
    }
}

public class TestImmutableObjects {
    public static void main(String[] args) throws InterruptedException {
        { // A Synchronized Class Example
            SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Black");
            new Thread(() -> color.set(255, 255, 255, "White")).start();
            new Thread(() -> {
                final int rgb;
                final String name;
                synchronized (color) {
                    rgb = color.getRGB();
                    name = color.getName();
                }
                final int red = (rgb >> 16) & 0xff, green = (rgb >> 8) & 0xff, blue = rgb & 0xff;
                System.out.println(name + ": (" + red + ", " + green + ", " + blue + ")");
            }).start();
        }
        { // A Strategy for Defining Immutable Objects
            ImmutableRGB color = new ImmutableRGB(0, 0, 255, "Blue");
            new Thread(() -> {
                ImmutableRGB invertedColor = color.invert();
                final int rgb = invertedColor.getRGB();
                final String name = invertedColor.getName();
                final int red = (rgb >> 16) & 0xff, green = (rgb >> 8) & 0xff, blue = rgb & 0xff;
                System.out.println(name + ": (" + red + ", " + green + ", " + blue + ")");

            }).start();
            new Thread(() -> {
                final int rgb = color.getRGB();
                final String name = color.getName();
                final int red = (rgb >> 16) & 0xff, green = (rgb >> 8) & 0xff, blue = rgb & 0xff;
                System.out.println(name + ": (" + red + ", " + green + ", " + blue + ")");
            }).start();
        }
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
