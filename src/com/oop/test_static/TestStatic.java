package com.oop.test_static;

class Mammal {
    protected String habitat;

    // Note: Static final attributes must be assigned value here or inside a static block
    public static final boolean isWarmBlooded;

    private static int numberOfLegs;

    // Note: The code inside a static block is executed only once: The first time the class is loaded into memory. 
    static { // Static block
        isWarmBlooded = true;
        numberOfLegs = 4;
    }

    public Mammal() {}

    public Mammal(String habitat) {
        this.habitat = habitat;
    }

    public static int getNumberOfLegs() {
        return numberOfLegs;
    }

    @Override
    public String toString() {
        return "This mammal";
    }
}

class Whale extends Mammal {
    public Whale() {
        habitat = "Ocean";
    }

    public static int getNumberOfLegs() {
        return 0;
    }

    @Override
    public String toString() {
        return "This whale";
    }
}

public class TestStatic {
    public static void main(String[] args) {
        { // Test static
            System.out.println("All mammals are " + (Mammal.isWarmBlooded ? "warm-blooded" : "cold-blooded"));
            System.out.println("Most mammals have " + Mammal.getNumberOfLegs() + " legs");
            Mammal mammal = new Mammal("Land");
        }
        { // Test method hiding
            Mammal mammal = new Whale();
            System.out.println(mammal + " has " + mammal.getNumberOfLegs() + " leg(s)");
            Whale whale = new Whale();
            System.out.println(whale + " has " + whale.getNumberOfLegs() + " leg(s)");
        }
        // Note:
        // 1. Instance methods belong to the object of the class, not to the class. They can be overridden since they are resolved using dynamic binding.
        // 2. Static methods belong to the class in which they reside. Static methods can not be overriden since they are resolved using static binding.
        // 3. Static variables and their values are stored in PermGen space of memory.
    }
}