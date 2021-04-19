import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.List;

class Book {
    private String title;
    
    public Book(String title) {
        this.title = title;
    } 

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book: " + this.title;
    }

    @Override
    public boolean equals(Object obj) {
        Book book = (Book)obj;
        return this.title.equals(book.title);
    }
}

public class TestArray {
    public static void print(int[] arr) { // Test passing arrays to methods
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "\n": ", "));
        }
    }

    public static int[] increase(int[] arr, int val) { // Test returning arrays
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] + val;
        }
        return res;
    }

    public static void main(String[] args) {
        { // Basic array
            { // Test creating and initializing an array
                { // Single dimensional array
                    int[] arr = new int[4];
                    print(arr);
                    int[] arr2 = new int[]{0, 1, 2, 3};
                    print(arr2);
                    int[] arr3 = {0, 1, 2, 3};
                    print(arr3);

                    Book[] books = new Book[4];
                    books[0] = new Book("Gone with the Wind");
                    books[1] = new Book("The Great Gatsby");
                    books[2] = new Book("To Kill a MockingBird");
                    books[3] = new Book("Little Women");
                    for (Book book : books) {
                        System.out.println(book);
                    }
                }
                { // Test multidimentional array
                    int[][] arr = {{0, 1, 2, 3}, {4, 5}, {6, 7, 8}, {9, 10, 11}};
                    for (int i = 0; i < arr.length; i++) {
                        print(arr[i]);
                    }
                }
            }
            { // Test accessing an array
                int[] arr = {0, 1, 2, 3};
                System.out.print("Original array: ");
                print(arr);

                System.out.print("After increasing by 1: ");
                for (int i = 0; i < arr.length; i++) {
                    arr[i] += 1;
                }
                print(arr);

                System.out.print("After increasing by 4: ");
                int[] res = increase(arr, 4);
                print(res);
            }
            { // Test cloning an array
                int[] original = {0, 1, 2, 3};
                System.out.print("Original array: ");
                print(original);

                int[] clone = original.clone();
                System.out.print("Clone array: ");
                print(clone);

                System.out.println("Original == Clone? " + (original == clone));
                for (int i = 0; i < original.length; i++) {
                    System.out.println("Original[i] == Clone[i] (i = " + i + ")? "+ (original[i] == clone[i]));
                }
            }
            { // Test default values
                {
                    int[] arr = new int[1];
                    System.out.println("Default value in an int array? " + arr[0]);
                }
                {
                    boolean[] arr = new boolean[1];
                    System.out.println("Default value in a bool array? " + arr[0]);
                }
                {
                    double[] arr = new double[1];
                    System.out.println("Default value in a doule array? " + arr[0]);
                }
                {
                    String[] arr = new String[1];
                    System.out.println("Default value in a String array? " + arr[0]);
                }
                {
                    Book[] arr = new Book[1];
                    System.out.println("Default value in an Object array? " + arr[0]);
                }
            }
        }
        { // java.util.Arrays, for manipulating arrays (such as sorting and searching)
            int[] arr = {2, 1, 0, 3};
            System.out.println("Array: " + Arrays.toString(arr));
            
            System.out.println("As list: " + Arrays.asList(arr));

            Arrays.sort(arr);
            System.out.println("After sorted: " + Arrays.toString(arr));

            int key = 1;
            System.out.println("Index of key 1 in the sorted array: " + Arrays.binarySearch(arr, key));
            System.out.println("Index of key 1 in the sorted array (from index 2 to index 3): " + Arrays.binarySearch(arr, 2, 3, key));

            System.out.println("A copy (with length 10): " + Arrays.toString(Arrays.copyOf(arr, 10)));
            System.out.println("A copy (from index 1 to index 2): " + Arrays.toString(Arrays.copyOfRange(arr, 1, 2 + 1)));

            // System.out.println("Compares to {0, 1, 3, 2}: " + Arrays.compare(arr, new int[]{0, 1, 3, 2}));      
            // System.out.println("Compares unsigned to {0, 1, 3, 2}: " + Arrays.compareUnsigned(arr, new int[]{0, 1, 3, 2}));
            System.out.println("Equals to {0, 1, 3, 2}? " + Arrays.equals(arr, new int[]{0, 1, 3, 2}));
            // System.out.println("Mismatchs with {0, 1, 3, 2} at index " + Arrays.mismatch(arr, new int[]{0, 1, 3, 2}));

            System.out.println("Hash code: " + Arrays.hashCode(arr));

            key = 4;
            Arrays.fill(arr, key);
            System.out.println("After filled with 4: " + Arrays.toString(arr));

            {
                Book[][] books = new Book[1][2];
                books[0][0] = new Book("Gone with the Wind");
                books[0][1] = new Book("To Kill a MockingBird");
                System.out.println("Array: " + Arrays.deepToString(books));
                Book[][] otherBooks = new Book[1][2];
                otherBooks[0][0] = new Book("Gone with the Wind");
                otherBooks[0][1] = new Book("The Great Gatsby");
                System.out.println("Deep equals to {{\"Gone with the Wind\", \"The Great Gatsby\"}}? " + Arrays.deepEquals(books, otherBooks));
                System.out.println("Deep hash code: " + Arrays.deepHashCode(books));
            }
        }
        { // java.lang.reflect.Array, for dynamically creating and accessing Java arrays, therefore keeping them to be type-safe
            int[] arr = (int[]) Array.newInstance(int.class, 4);
            for (int i = 0; i < arr.length; i++) {
                Array.setInt(arr, i, i);
            }
            System.out.println(Arrays.toString(arr));
            for (int i = 0; i < arr.length; i++) {
                System.out.println("Element at index " + i + ": " + Array.getInt(arr, i));
            }
        }
        // Note: 
        // 1. In Java, all arrays are dynamically allocated.
        // 2. Unlike C++, arrays are first class objects in Java.
    }
}