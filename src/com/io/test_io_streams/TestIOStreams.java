package com.io.test_io_streams;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

class Password {
    static void run() {
        Console console = System.console();
        if (console == null) {
            System.out.println("There is no console.");
            return;
        }

        String username = console.readLine("Enter your username: ");
        char[] oldPassword = console.readPassword("Enter your old password: ");
        if (verify(username, oldPassword)) {
            boolean matched;
            do {
                char[] newPassword1 = console.readPassword("Enter your new password: ");
                char[] newPassword2 = console.readPassword("Enter new password again: ");
                matched = Arrays.equals(newPassword1, newPassword2);
                if (!matched) {
                    console.format("Passwords don't match. Try again.%n");
                } else {
                    change(username, newPassword1);
                    console.format("Password for %s changed.%n", username);
                }
                Arrays.fill(newPassword1, ' ');
                Arrays.fill(newPassword2, ' ');
            } while (!matched);
        }
        Arrays.fill(oldPassword, ' ');
    }

    static boolean verify(String username, char[] password) {
        // Dummy
        return true;
    }

    static void change(String username, char[] password) {
        // Dummy
    }
}

public class TestIOStreams {
    public static void main(String[] args) throws InterruptedException, IOException {
        { // Byte Streams
            startTest("TEST BYTE STREAMS");

            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                in = new FileInputStream("resources/input.txt");
                out = new FileOutputStream("out/output1.txt");
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            endTest();
        }
        { // Character Streams
            startTest("TEST CHARACTER STREAMS");

            FileReader in = null;
            FileWriter out = null;
            try {
                in = new FileReader("resources/input.txt");
                out = new FileWriter("out/output2.txt");
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            endTest();
        }
        { // Buffered Streams
            startTest("TEST BUFFERED STREAMS");

            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new FileReader("resources/input.txt"));
                out = new PrintWriter(new FileWriter("out/output3.txt"));
                String l;
                while ((l = in.readLine()) != null) {
                    out.println(l);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            endTest();
        }
        { // Scanning and Formatting
            { // Scanning
                startTest("TEST SCANNING");

                Scanner scanner = null;
                try {
                    scanner = new Scanner(new BufferedReader(new FileReader("resources/input.txt")));
                    while (scanner.hasNext()) {
                        System.out.println(scanner.next());
                    }
                } finally {
                    if (scanner != null) {
                        scanner.close();
                    }
                }

                endTest();
            }
            { // Formatting
                startTest("TEST FORMATTING");

                int val = 4;
                System.out.println(val + "^2 = " + val * val);
                System.out.format("%d^2 = %d%n", val, val * val);

                endTest();
            }
        }
        { // I/O from the Command Line
            startTest("TEST CONSOLE");

            Password.run();

            endTest();
        }
        { // Data Streams
            startTest("TEST DATA STREAMS");

            final String dataFile = "out/data.txt";
            final double[] prices = {3.99, 11.99, 12.99, 24.99};
            final int[] units = {25, 13, 12, 4};
            final String[] descs = {"Picture", "Mug", "T-shirt", "Book"};

            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
            for (int i = 0; i < prices.length; ++i) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
            out.flush();
            out.close();

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
            double price;
            int unit;
            String desc;
            double total = 0.0;
            try {
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n", unit, desc, price);
                    total += unit * price;
                }
            } catch (EOFException exception) {}
            System.out.format("Total: $%.2f%n", total);
            in.close();

            endTest();
        }
        { // Object Streams
            startTest("TEST OBJECT STREAMS");

            final String dataFile = "out/data.txt";
            SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
            final Calendar today = new GregorianCalendar(2021, Calendar.AUGUST, 9, 9, 30, 0);
            final BigDecimal[] prices = {new BigDecimal("3.99"), new BigDecimal("11.99"), new BigDecimal("12.99"), new BigDecimal("24.99")};
            final int[] units = {25, 13, 12, 4};
            final String[] descs = {"Picture", "Mug", "T-shirt", "Book"};

            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
            out.writeObject(today);
            for (int i = 0; i < prices.length; ++i) {
                out.writeObject(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
            Calendar date;
            BigDecimal price;
            int unit;
            String desc;
            double total = 0.0;
            try {
                date = (Calendar) in.readObject();
                System.out.format("Date: %s%n", format.format(date.getTime()));
                while (true) {
                    price = (BigDecimal) in.readObject();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n", unit, desc, price);
                    total += unit * price.doubleValue();
                }
            } catch (EOFException | ClassNotFoundException exception) {}
            System.out.format("Total: $%.2f%n", total);
            in.close();

            endTest();
        }
    }

    public static void startTest(String resources) {
        System.out.println(resources);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
