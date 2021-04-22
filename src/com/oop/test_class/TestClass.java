package com.oop.test_class;

class Album { // Class default access
    private String title;
    private String artist;
    private int year;

    // Constructor

    public Album(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    // Copy constructor

    public Album(Album original) {
        this(original.title, original.artist, original.year);
    }

    // Getters and setters

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    int getYear() {
        return year;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setArtist(String title) {
        this.artist = artist;
    }

    void setYear(int year) {
        this.year = year;
    }

    // Overridden methods

    @Override
    public String toString() {
        return artist + " - " + title + " (" + year + ")"; 
    }
};

class Artist implements Cloneable { // Class default access
    private String name;

    // Constructor

    public Artist(String name) {
        this.name = name;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Overridden methods

    @Override
    public String toString() {
        return "Artist: " + name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int id = 0; id < name.length(); id++) {
            hash += name.charAt(id);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) obj;
        return this.name.equals(artist.name);
    }

    @Override
    protected void finalize() {
        System.out.println("Destroy artist: " + name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Shallow copy
        return super.clone();
        
        // Deep copy
        // Artist artist = (Artist)super.clone();
        // artist.name = new String(name);
        // return artist;
    }

    // Other methods in Object (getClass(), wait(), notify(), notifyAll())
}

class Floor { // Outer class 
    private static int fWidth;

    private static int fHeight;

    private String fColor;

    public class CarpetManager { // Inner class 
        private int cWidth ;

        private int cHeight;

        CarpetManager(int width, int height) {
            cWidth = width;
            cHeight = height;
        }

        public int getNumberOfCarpets() {
            return (fWidth * fHeight) / (cWidth * cHeight);
        }

        public String getCarpetColor() {
            return fColor;
        }
    }

    // Note:
    // 1. Only inner classes can be static
    // 2. A static nested class may be instantiated without instantiating its outer class
    // 3. A static class can access only the static members of the outer class
    public static class TileManager { // Inner class
        public int tWidth = 1;

        public int tHeight = 1;

        public int getNumberOfTiles() {
            return (fWidth * fHeight) / (tWidth * tHeight);
        }
    }

    Floor(int width, int height, String color) {
        fWidth = width;
        fHeight = height;
        fColor = color;
    }
}

class Flower {
    private String name;

    // Note: Initialization blocks are executed whenever the class is initialized and before constructors are invoked
    { // Initializer block
        System.out.println("This is a flower");
    }

    public Flower(String name) {
        this.name = name;
        System.out.println("This is a " + this.name);
    }
}

public class TestClass { // Class public access
    // Note: main should always be public static to make it clear for the JVM to call it for launching the Java application
    public static void main(String[] args) throws CloneNotSupportedException {
        { // Test creating objects of a class
            Album tsFearless = new Album("Fearless", "Taylor Swift", 2008);

            Album tsSpeakNow;
            tsSpeakNow = new Album("Speak Now", "Taylor Swift", 2010);

            Album tsRed = new Album(tsSpeakNow);
            tsRed.setTitle("Red");
            tsRed.setYear(2012);

            System.out.println(tsFearless);
            System.out.println(tsSpeakNow);
            System.out.println(tsRed);
        }
        { // Test creating multiple objects of a class by one type only (Good practice)
            Album album = new Album("1989", "Taylor Swift", 2014);
            System.out.println(album);

            album = new Album("reputation", "Taylor Swift", 2017); // Destroyed "1989" object
            System.out.println(album);
        }
        { // Test anonymous objects
            String title = new Album("folklore", "Taylor Swift", 2020).getTitle();
            System.out.println(title);
        }
        { // Test Object class
            { // Object structures
                Artist artist = new Artist("Phoebe Bridgers");
                System.out.println(artist);
                System.out.println("Hash code: " + artist.hashCode());
                Artist anotherArtist = new Artist("Fiona Apple");
                System.out.println("Is equal " + anotherArtist.getName() + "? " + artist.equals(anotherArtist));
                anotherArtist = (Artist)artist.clone();
                anotherArtist.setName("Fiona Apple");
                System.out.println(artist);
                System.out.println(anotherArtist);
            }
            { // Object flexibility
                Object obj;
                obj = true;
                System.out.println(obj.getClass().getName());
                obj = 251096;
                System.out.println(obj.getClass().getName());
                obj = "Pham Huu Bao Chung";
                System.out.println(obj.getClass().getName());
                obj = new Album("Punisher", "Phoebe Bridgers", 2020);
                System.out.println(obj.getClass().getName());
            }
        }
        { // Test inner classes
            Floor floor = new Floor(200, 100, "Pink");
            Floor.CarpetManager carpetManager = floor.new CarpetManager(20, 10);
            System.out.println("Number of carpets: " + carpetManager.getNumberOfCarpets());
            System.out.println("Carpet color: " + carpetManager.getCarpetColor());
            Floor.TileManager tileManager = new Floor.TileManager();
            tileManager.tWidth = 2;
            tileManager.tHeight = 1;
            System.out.println("Number of tiles: " + tileManager.getNumberOfTiles());
        }
        { // Test instance initialization block
            Flower flower = new Flower("rose");
        }
        // Note:
        // 1. In Java, all objects are dynamically allocated on Heap
        // 2. Access modifiers
        //                                    | private    | default    | private    | public     
        //     -------------------------------|------------|------------|------------|------------
        //     Same class                     | Yes        | Yes        | Yes        | Yes        
        //     Same package subclass          | No         | Yes        | Yes        | Yes        
        //     Same package non-subclass      | No         | Yes        | Yes        | Yes        
        //     Different package subclass     | No         | No         | Yes        | Yes        
        //     Different package non-subclass | No         | No         | No         | Yes        
    }
}