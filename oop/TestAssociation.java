import java.util.List;
import java.util.ArrayList;

class Country {
    private String name;

    // Constructor

    public Country(String name) {
        this.name = name;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class River {
    private String name;

    // Constructor

    public River(String name) {
        this.name = name;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Employee {
    private String name;

    // Constructor

    public Employee(String name) {
        this.name = name;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Factory {
    private String name;
    private List<Employee> employees;

    // Constructor

    public Factory(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Memory {
    private final int capacity;

    // Constructor

    public Memory(int capacity) {
        this.capacity = capacity;
    }

    // Getters and setters

    public int getCapacity() {
        return capacity;
    }

    // Methods

    public void config() {
        System.out.println("Memory: " + capacity / 1024 + "G");
    }

    public void run() {
        System.out.println("Run memory");
    }

    public void stop() {
        System.out.println("Stop memory");
    }
}

class Computer {
    private final String brand;
    private final Memory memory; // Use final to imply Composition relationship?

    // Constructor

    public Computer(String brand, Memory memory) {
        this.brand = brand;
        this.memory = memory;
    }

    // Getters and setters

    public String getBrand() {
        return brand;
    }

    public Memory getMemory() {
        return memory;
    }

    // Methods

    public void config() {
        System.out.println("Computer: " + brand);
        memory.config();
    }

    public void run() {
        System.out.println("Run computer");
        memory.run();
        System.out.println("Do some tasks");
    }

    public void stop() {
        System.out.println("Stop computer");
        memory.stop();
    }
}

public class TestAssociation {
    public static void main(String[] args) {
        { // Test Association
            Country country = new Country("Vietnam");
            River river = new River("Red");
            System.out.println(river.getName() + " is a river in " + country.getName());
        }
        { // Test Aggregation
            List<Employee> employees = new ArrayList<Employee>();
            Employee employee1 = new Employee("Oliver");
            Employee employee2 = new Employee("Jack");
            Employee employee3 = new Employee("Harry");
            employees.add(employee1);
            employees.add(employee2);
            employees.add(employee3);
            Factory factory = new Factory("Aigialeia", employees);
            int numEmployees = factory.getAllEmployees().size();
            System.out.println("Factory " + factory.getName() + " has " + numEmployees + " employees");
        }
        { // Test Composition
            Memory memory = new Memory(4096);
            Computer computer = new Computer("Intel", memory);
            computer.config();
            computer.run();
            computer.stop();
        }
        // Note:
        //     Differences      | Aggregation                           | Composition                           
        //     -----------------|---------------------------------------|---------------------------------------
        //     Dependency       | The "child" can exist independently   | The "child" can't exist independently 
        //     Relationship     | has-a                                 | part-of                               
        //     Association type | Weak                                  | Strong                                
    }
}