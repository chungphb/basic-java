package com.basics.test_primitive_data_types;

public class TestPrimitiveDataTypes {
    public static void main(String[] args) {
        boolean boolVal = true;
        System.out.printf("Boolean variable: %b%n", boolVal);

        byte byteVal = (byte)(0xFF + 1);
        System.out.printf("Byte variable: 0x%02X%n", byteVal);

        short shortVal = (short)(32767 + 1);
        System.out.printf("Short variable: %d%n", shortVal);

        int intVal = 2147483647 + 1;
        System.out.printf("Int variable: %d%n", intVal);

        long longVal = 9223372036854775807L + 1;
        System.out.printf("Long variable: %d%n", longVal);

        float floatVal = 25.10f;
        System.out.printf("Float variable: %.2f%n", floatVal);

        double doubleVal = 5.121996;
        System.out.printf("Double variable: %.4f%n", doubleVal);

        char charVal = 'C';
        System.out.printf("Char variable: %c%n", charVal);
    }
}