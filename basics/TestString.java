import java.nio.charset.Charset; // For Charset
import java.util.*; // For Objects.equals(String, String)

public class TestString { // Running with -encoding UTF-8 option
    public static void main(String[] args) {
        { // String
            { // Test creating a string
                { // Using string literal
                    String str = "String";
                    System.out.println(str);
                }
                { // Using new keyword
                    String str = new String("String");
                    System.out.println(str);
                }
                { // Using byte array
                    byte[] arr = {83, 116, 114, 105, 110, 103};

                    String str = new String(arr);
                    System.out.println(str);

                    str = new String(arr, Charset.defaultCharset());
                    System.out.println(str);

                    try {
                        str = new String(arr, "US-ASCII");
                        System.out.println(str);
                    } catch (Exception ex) {}

                    str = new String(arr, 2, 4);
                    System.out.println(str);
                    
                    str = new String(arr, 2, 4, Charset.defaultCharset());
                    System.out.println(str);

                    try {
                        str = new String(arr, 2, 4, "US-ASCII");
                        System.out.println(str);
                    } catch (Exception ex) {}
                }
                { // Using character array
                    char[] arr = {'S', 't', 'r', 'i', 'n', 'g'};
                    
                    String str = new String(arr);
                    System.out.println(str);

                    str = new String(arr, 2, 4);
                    System.out.println(str);
                }
                { // Using unicode array
                    int[] arr = {83, 116, 114, 105, 110, 103};
                    String str = new String(arr, 2, 4);
                    System.out.println(str);
                }
                { // Using StringBuffer
                    StringBuffer strBuffer = new StringBuffer("String");
                    String str = new String(strBuffer);
                    System.out.println(str);
                }
                { // Using StringBuilder
                    StringBuilder strBuilder = new StringBuilder("String");
                    String str = new String(strBuilder);
                    System.out.println(str);
                }
            }
            { // Test manipulating a string
                String str = "Love is Love";
                System.out.println("String: " + str);
                System.out.println("Length: " + str.length());
                System.out.println("Char at index 0: " + str.charAt(0));
                System.out.println("Substring starting at index 8: " + str.substring(8));
                System.out.println("Substring from index 8 to index 11: " + str.substring(8, 11 + 1));
                System.out.println("To upper case: " + str.toUpperCase());
                System.out.println("To lower case: " + str.toLowerCase());
                System.out.println("After trimming: " + str.trim());
                System.out.println("After replacing L by l: " + str.replace('L', 'l'));
            }
            { // Test manipulating two (or more) strings
                String str1 = "Love is Love";
                String str2 = "Love";
                String str3 = "love";
                System.out.println("String 1 (S1): " + str1);
                System.out.println("String 2 (S2): " + str2);
                System.out.println("String 3 (S3): " + str3);
                System.out.println("S1 + S2 = " + (str1 + str2));
                System.out.println("Index of S2 in S1: " + str1.indexOf(str2));
                System.out.println("Index of S2 in S1 (starting at index 5): " + str1.indexOf(str2, 5));
                System.out.println("Last index of S2 in S1: " + str1.lastIndexOf(str2));
                int res = str1.compareTo(str2);
                System.out.println("S1 " + (res > 0 ? ">" : (res == 0 ? "=" : "<")) + " S2");
                res = str1.compareToIgnoreCase(str2);
                System.out.println("S1 " + (res > 0 ? ">" : (res == 0 ? "=" : "<")) + " S2 (ignoring case)");
                System.out.println("S2 is equal to S3? " + str2.equals(str3));
                System.out.println("S2 is equal to S3? " + str2.equalsIgnoreCase(str3) + " (ignoring case)");
                CharSequence charSeq = "is";
                System.out.println("Char Sequence (CS): " + charSeq);
                System.out.println("Does S1 contain CS? " + str1.contains(charSeq));
                System.out.println("Does S2 contain CS? " + str2.contains(charSeq));
                System.out.println("Does S1 start with S2? " + str1.startsWith(str2));
                System.out.println("Does S1 start with S2 (from index 8)? " + str1.startsWith(str2, 8));
                System.out.println("Does S1 end with S2? " + str1.endsWith(str2));
            }
            { // Test converting integer to string
                { // Using Integer.toString(int)
                    int val = 25101996;
                    String str = Integer.toString(val);
                    System.out.println(str);
                }
                { // Using String.valueOf(int)
                    int val = 25101996;
                    String str = String.valueOf(val);
                    System.out.println(str);
                }
                { // Using Integer(int).toString()
                    int val = 25101996;
                    String str = new Integer(val).toString();
                    System.out.println(str);
                }
                { // Using StringBuffer or StringBuilder
                    int val = 25101996;

                    String str = new StringBuffer().append(val).toString();
                    System.out.println(str);

                    str = new StringBuilder().append(val).toString();
                    System.out.println(str);
                }
                { // Converting to binary, octal, hexadecimal and base 4 system
                    int val = 25101996;

                    String binaryStr = Integer.toBinaryString(val);
                    System.out.println(binaryStr);
                    
                    String octalStr = Integer.toOctalString(val);
                    System.out.println(octalStr);

                    String hexStr = Integer.toHexString(val);
                    System.out.println(hexStr);

                    String base4Str = Integer.toString(val, 4);
                    System.out.println(base4Str);
                }
            }
            { // Test converting string to integer
                { // Using Integer.parseInt(String)
                    String str = "25101996";
                    int val;

                    val = Integer.parseInt(str);
                    System.out.println(val);

                    val = Integer.parseInt(str, 16);
                    System.out.println(val);
                }
                { // Using Integer.valueOf(String)
                    String str = "-25101996";
                    int val;

                    val = Integer.valueOf(str);
                    System.out.println(val);

                    val = Integer.valueOf(str, 16);
                    System.out.println(val);
                }
                { // Parsing long and double
                    String longStr = "9223372036854775807";
                    System.out.println(Long.parseLong(longStr));

                    String doubleStr = "25.101996";
                    System.out.println(Double.parseDouble(doubleStr));
                }
            }
            { // Test comparing two strings
                String str1 = "Love is Love";
                String str2 = "Love";
                String str3 = "Love";
                String str4 = new String("Love");
                System.out.println("String 1 (S1): " + str1);
                System.out.println("String 2 (S2): " + str2);
                System.out.println("String 3 (S3): " + str3);
                System.out.println("String 4 (S4): " + str4 + " (using new)");
                { // Using String.equals(String)
                    System.out.println("S1 is equal to S2? " + str1.equals(str2));
                    System.out.println("S2 is equal to S3? " + str2.equals(str3));
                }
                { // Using Objects.equals(String, String)
                    System.out.println("S1 is equal to S2? " + Objects.equals(str1, str2));
                    System.out.println("S2 is equal to S3? " + Objects.equals(str2, str3));
                }
                { // Using String.compareTo()
                    int res = str1.compareTo(str2);
                    System.out.println("S1 " + (res > 0 ? ">": (res == 0 ? "=" : "<")) + " S2");
                    res = str2.compareTo(str3);
                    System.out.println("S2 " + (res > 0 ? ">": (res == 0 ? "=" : "<")) + " S3");
                }
                { // Using == for address comparison and .equals() for content comparison
                    System.out.println("S2 == S3? " + (str2 == str3));
                    System.out.println("S2 == S4? " + (str2 == str4));
                }
            }
        }
        { // StringBuffer 
            { // Test querying string buffer
                StringBuffer str = new StringBuffer("爱 is Love");
                System.out.println("String Buffer: " + str);
                System.out.println("Length: " + str.length());
                System.out.println("Capacity: " + str.capacity());
                System.out.println("Char at index 2: " + str.charAt(2));
                System.out.println("Unicode code point at index 0: " + str.codePointAt(0));
                System.out.println("Unicode code point before index 1: " + str.codePointBefore(1));
                System.out.println("Number of Unicode code points from index 0 to index 2: " + str.codePointCount(0, 2 + 1));
                System.out.println("Index of \"Love\": " + str.indexOf("Love"));
                System.out.println("Last index of \"Love\": " + str.lastIndexOf("Love"));
                System.out.println("Offset from index 0 by 5 code points: " + str.offsetByCodePoints(0, 5));
                System.out.println("Subsequence from index 5 to index 8: " + str.subSequence(5, 8 + 1));
                System.out.println("Substring starting at index 5: " + str.substring(5));
                System.out.println("To string: " + str.toString());
            }
            { // Test updating string buffer
                StringBuffer str = new StringBuffer("Love");
                System.out.println("String Buffer: " + str);

                str.setCharAt(0, 'l');
                System.out.println("After setting char at index 0 to \'l\': " + str);

                str.append("r");
                System.out.println("After appending \"r\": " + str);

                str.insert(0, "My ");
                System.out.println("After inserting \"My \" at index 0: " + str);

                str.reverse();
                System.out.println("After reversing: " + str);

                str.delete(2, 5 + 1);
                System.out.println("After deleting chars from index 2 to index 5: " + str);

                str.deleteCharAt(3);
                System.out.println("After deleting char at index 3: " + str);

                str.replace(2, 3, "d");
                System.out.println("After replacing \"y\" by \"d\": " + str);
                
                int capacity = str.capacity();
                str.ensureCapacity(25);
                System.out.println("Increase capacity from " + capacity + " to " + str.capacity());

                str.appendCodePoint(63);
                System.out.println("After appending Unicode code point 63: " + str);

                str.setLength(25);
                System.out.println("Set length to: " + str.length());

                str.trimToSize();
                System.out.println("After trimming to size: Length = " + str.length() + ", Capacity = " + str.capacity());
            }
        }
        { // StringBuilder (similar to StringBuffer, however, is not safe for use by multiple threads)
            { // Test querying string buffer
                StringBuilder str = new StringBuilder("恨 is Hate");
                System.out.println("String Builder: " + str);
                System.out.println("Length: " + str.length());
                System.out.println("Capacity: " + str.capacity());
                System.out.println("Char at index 2: " + str.charAt(2));
                System.out.println("Unicode code point at index 0: " + str.codePointAt(0));
                System.out.println("Unicode code point before index 1: " + str.codePointBefore(1));
                System.out.println("Number of Unicode code points from index 0 to index 2: " + str.codePointCount(0, 2 + 1));
                System.out.println("Index of \"Hate\": " + str.indexOf("Hate"));
                System.out.println("Last index of \"Hate\": " + str.lastIndexOf("Hate"));
                System.out.println("Offset from index 0 by 5 code points: " + str.offsetByCodePoints(0, 5));
                System.out.println("Subsequence from index 5 to index 8: " + str.subSequence(5, 8 + 1));
                System.out.println("Substring starting at index 5: " + str.substring(5));
                System.out.println("To string: " + str.toString());
            }
            { // Test updating string buffer
                StringBuilder str = new StringBuilder("Hate");
                System.out.println("String Builder: " + str);

                str.setCharAt(0, 'h');
                System.out.println("After setting char at index 0 to \'h\': " + str);

                str.append("r");
                System.out.println("After appending \"r\": " + str);

                str.insert(0, "My ");
                System.out.println("After inserting \"My \" at index 0: " + str);

                str.reverse();
                System.out.println("After reversing: " + str);

                str.delete(2, 5 + 1);
                System.out.println("After deleting chars from index 2 to index 5: " + str);

                str.deleteCharAt(3);
                System.out.println("After deleting char at index 3: " + str);

                str.replace(2, 3, "d");
                System.out.println("After replacing \"y\" by \"d\": " + str);
                
                int capacity = str.capacity();
                str.ensureCapacity(25);
                System.out.println("Increase capacity from " + capacity + " to " + str.capacity());

                str.appendCodePoint(63);
                System.out.println("After appending Unicode code point 63: " + str);

                str.setLength(25);
                System.out.println("Set length to: " + str.length());

                str.trimToSize();
                System.out.println("After trimming to size: Length = " + str.length() + ", Capacity = " + str.capacity());
            }
            // String buffers are safe for use by multiple threads
        }
        { // Test StringTokenizer
            String str;
            StringTokenizer strTokenizer;
            {
                str = new String("Love is\tLove\n");
                strTokenizer = new StringTokenizer(str);
                System.out.println("String: " + str);
                System.out.println("Number of tokens: " + strTokenizer.countTokens());
                System.out.print("Tokens: ");
                while (strTokenizer.hasMoreTokens()) {
                    System.out.print("[" + strTokenizer.nextToken() + "] ");
                }
                System.out.println();
            }
            {
                str = new String("Love;is;Love");
                strTokenizer = new StringTokenizer(str, ";", true); // Including delimiters as tokens
                System.out.println("String: " + str);
                System.out.println("Number of tokens: " + strTokenizer.countTokens());
                System.out.print("Tokens: ");
                while (strTokenizer.hasMoreTokens()) {
                    System.out.print("[" + strTokenizer.nextToken() + "] ");
                }
                System.out.println();
            }
            
        }
        { // Test StringJoiner
            ArrayList<String> strList = new ArrayList<>();
            strList.add("Taylor Swift");
            strList.add("Fearless");
            strList.add("Speak Now");
            strList.add("Red");
            strList.add("1989");
            strList.add("reputation");
            strList.add("Lover");
            strList.add("folklore");
            strList.add("evermore");
            StringJoiner strJoiner = new StringJoiner(", ");
            strJoiner.setEmptyValue("Empty");
            System.out.println(strJoiner);
            for (int id = 0; id < strList.size() / 2; id++) {
                strJoiner.add(strList.get(id));
            }
            System.out.println(strJoiner);
            StringJoiner strJoiner2 = new StringJoiner(": ");
            strJoiner2.setEmptyValue("Empty");
            System.out.println(strJoiner2);
            for (int id = strList.size() / 2; id < strList.size(); id++) {
                strJoiner2.add(strList.get(id));
            }
            System.out.println(strJoiner2);
            strJoiner.merge(strJoiner2);
            System.out.println(strJoiner);
        }
    }
}