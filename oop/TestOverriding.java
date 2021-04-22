class Phone {
    void config() {
        System.out.println("Config phone");
    }

    static void run() {
        System.out.println("Run phone");
    }

    final void handleEvent() {
        System.out.println("Handle event");
        save();
    }

    private void save() {
        System.out.println("Save data");
    }
}

class Smartphone extends Phone {
    @Override
    protected void config() {
        System.out.println("Config smartphone");
    }

    static void run() {
        System.out.println("Run smartphone");
    }

    // void handleEvent() {}

    private void save() {
        System.out.println("Save data of smartphone");
    }
}

class PhoneOwner {
    public Phone getNewPhone() {
        return new Phone();
    }

    public void complain() {
        System.out.println("Call to complain");
    }

    public void report() throws RuntimeException {
        System.out.println("Call to report");
    }
}

class SmartphoneOwner extends PhoneOwner {
    @Override
    public Phone getNewPhone() {
        return new Smartphone();
    }

    @Override
    public void complain() throws ArithmeticException {
        System.out.println("Mail to complain");
    }

    @Override
    public void report() throws ArithmeticException {
        System.out.println("Mail to report");
    }

    // @Override
    // public void complain() throws Exception {
    //     System.out.println("Mail to complain");
    // }

    // @Override
    // public void report() throws Exception {
    //     System.out.println("Mail to report");
    // }
}

public class TestOverriding {
    public static void main(String[] args) {
        { // Test overriding
            Phone phone = new Smartphone();
            phone.config();
            phone.run();
            phone.handleEvent();
        }
        { // Test covariant return types
            PhoneOwner phoneOwner = new SmartphoneOwner();
            Phone phone = phoneOwner.getNewPhone();
            phone.config();
            phone.run();
            phone.handleEvent();
        }
        { // Test overriding and exception handling
            PhoneOwner phoneOwner = new SmartphoneOwner();
            phoneOwner.complain();
            phoneOwner.report();
        }
        // Note:
        // 1. The access modifier for an overriding method can allow more, but not less, access than the overridden method
        // 2. Final methods can not be overridden
        // 3. Static methods can not be overridden
        // 4. Private methods can not be overridden
        // 5. The overriding method must have same return type (or subtype)
        // 6. Exception handling when overrding 
        //    a) If the super-class overridden method does not throw an exception, subclass overriding method can only throws the unchecked exception
        //    b) If the super-class overridden method does throw an exception, subclass overriding method can only throw same, subclass exception
    }
}