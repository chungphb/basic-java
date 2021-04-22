package com.oop.test_abstraction;

enum Color {
    RED, GREEN, BLUE;
}

abstract class Shape {
    private Color color;

    // Constructor

    public Shape(Color color) {
        this.color = color;
    }

    // Getters and setters

    public Color getColor() {
        return color;
    }

    // Abstract methods

    public abstract double getPerimeter();

    public abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    // Constructor

    public Circle(Color color, double radius) {
        super(color);
        this.radius = radius;
    }

    // Overridden methods

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "[C] Color: " + super.getColor() + "; Perimeter: " + getPerimeter() + "; Area = " + getArea();
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    // Constructor

    public Rectangle(Color color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    // Overridden methods

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public String toString() {
        return "[R] Color: " + super.getColor() + "; Perimeter: " + getPerimeter() + "; Area = " + getArea();
    }
}

public class TestAbstraction {
    public static void main(String[] args) {
        Shape circle = new Circle(Color.RED, 4.0);
        Shape rectangle = new Rectangle(Color.BLUE, 2.5, 1.0);
        System.out.println(circle);
        System.out.println(rectangle);
    }
}