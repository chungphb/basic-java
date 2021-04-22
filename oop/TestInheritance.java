import java.util.Arrays;

enum Gender {MEN, WOMEN, NONBINARY}

class Person { // Superclass, Base class or Parent class
    private String name;
    private int age;
    private Gender gender;

    // Constructor

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getters and setters

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    Gender getGender() {
        return gender;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setGender(Gender gender) {
        this.gender = gender;
    }

    // Overridden methods

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}

class Employee extends Person { // Subclass, Derived class or Child class
    private String company;
    private int id;

    // Constructor

    Employee(String name, int age, Gender gender, String company, int id) {
        super(name, age, gender);
        this.company = company;
        this.id = id;
    }

    // Getters and setters

    String getCompany() {
        return company;
    }
    
    int getID() {
        return id;
    }

    void setCompany(String company) {
        this.company = company;
    }

    void setID(int id) {
        this.id = id;
    }

    int getSalary() {
        return 500;
    }

    // Overridden methods

    @Override
    public String toString() {
        return super.toString() + "\nWorks at: " + company + "\nID: " + id + "\nSalary: " + getSalary();
    }
}

class Manager extends Employee {
    Manager(String name, int age, Gender gender, String company, int id) {
        super(name, age, gender, company, id);
    }

    // Overridden methods

    @Override
    int getSalary() {
        return 1000;
    }
}

class Singer extends Person {
    private String[] albums;

    // Constructor

    Singer(String name, int age, Gender gender, String[] albums) {
        super(name, age, gender);
        this.albums = albums;
    }

    // Getters and setters

    String[] getAlbums() {
        return albums;
    }

    void setAlbums(String[] albums) {
        this.albums = albums;
    }

    // Overriden methods

    @Override
    public String toString() {
        return super.toString() + "\nAlbums: " + Arrays.toString(albums);
    }
}

interface InterfaceA {
    public void runA();
}

interface InterfaceB {
    public void runB();
}

class Foo implements InterfaceA, InterfaceB {
    @Override
    public void runA() {
        System.out.println("A");
    }

    @Override
    public void runB() {
        System.out.println("B");
    }
}

public class TestInheritance {
    public static void main(String[] args) {
        final Person BERNIE = new Person("Bernie Sanders", 79, Gender.MEN);
        System.out.println(BERNIE);
        { // Test single inheritance
            final Employee ME = new Employee("Pham Huu Bao Chung", 25, Gender.MEN, "VHT", 251096);
            System.out.println(ME);
        }
        { // Test multilevel inheritance
            final Employee JAMES = new Manager("James Anderson", 28, Gender.NONBINARY, "VHT", 100000);
            System.out.println(JAMES);
        }
        { // Test hierarchical inheritance
            final Singer TAYLOR = new Singer("Taylor Swift", 31, Gender.WOMEN, new String[]{"Fearless", "Red", "1989", "folklore", "evermore"});
            System.out.println(TAYLOR);
        }
        { // Test multiple inheritance (via interfaces)
            Foo foo = new Foo();
            foo.runA();
            foo.runB();
        }
    }
}