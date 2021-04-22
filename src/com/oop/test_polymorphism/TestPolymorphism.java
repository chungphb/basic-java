package com.oop.test_polymorphism;

class Person {
    private String name;
    private String id;
    String data;

    // Constructor

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.data = this.name + " is a person";
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    // Overridden methods

    @Override
    public String toString() {
        return "Person: " + name + "; ID: " + id;
    }
}

class Student extends Person {
    private String id;
    String data;

    // Constructor

    public Student(String name, String id) {
        super(name, id);
        this.id = "S" + id;
        this.data = super.getName() + " is a student";
    }

    // Overridden methods

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {
        super.setID(id);
        this.id = "S" + id;
    }

    @Override
    public String toString() {
        return "Student: " + super.getName() + "; ID: " + super.getID();
    }
}

class Teacher extends Person {
    private String id;
    private String subject;

    public Teacher(String name, String id, String subject) {
        super(name, id);
        this.id = "T" + id;
        this.subject = subject;
    }

    // Getters and setters

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Overridden methods

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {
        super.setID(id);
        this.id = "T" + id;
    }

    @Override
    public String toString() {
        return "Teacher: " + super.getName() + "; ID: " + super.getID() + "; Subject: " + subject;
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        { // Test Dynamic Method Dispatch
            Person person = new Person("Pham Huu Bao Chung", null);
            System.out.println(person);

            Student student = new Student("Pham Huu Bao Chung", "251096");
            System.out.println(student);

            Teacher teacher = new Teacher("Pham Huu Bao Chung", "251096", "Math");
            System.out.println(teacher);

            System.out.println(person.getID());

            person = student;
            System.out.println(person.getID());
            
            person = teacher;
            System.out.println(person.getID());
        }
        { // Test Runtime Polymorphism with Data Members (Note: Runtime polymorphism cannot be achieved by data members)
            Person person = new Student("Pham Huu Bao Chung", "251096");
            System.out.println(person.data);
        }
    }
}