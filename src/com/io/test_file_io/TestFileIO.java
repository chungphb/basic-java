package com.io.test_file_io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;

import static java.nio.file.StandardCopyOption.*;

public class TestFileIO {
    public static void main(String[] args) throws IOException {
        { // What Is a Path?
            // What Is a Path?
            // Relative or Absolute?
            // Symbolic Links
        }
        { // The Path Class
            startTest("TEST PATH");

            // Creating a Path
            Path path = Paths.get("./resources/folder");

            // Retrieving Information About a Path
            System.out.format("toString: %s%n", path.toString());
            System.out.format("getFileName: %s%n", path.getFileName());
            System.out.format("getParent: %s%n", path.getParent());
            System.out.format("getRoot: %s%n", path.getRoot());
            int nameCount = path.getNameCount();
            System.out.format("getNameCount: %s%n", nameCount);
            for (int i = 0; i < nameCount; ++i) {
                System.out.format("getName(%d): %s%n", i, path.getName(i));
            }
            System.out.format("subpath(0, %d): %s%n", nameCount - 1, path.subpath(0, nameCount - 1));

            // Removing Redundancies from a Path
            path = path.normalize();
            System.out.format("toString (after being normalized): %s%n", path.toString());

            // Converting a Path
            System.out.format("toUri: %s%n", path.toUri());
            System.out.format("toAbsolutePath: %s%n", path.toAbsolutePath());
            System.out.format("toRealPath: %s%n", path.toRealPath());

            // Joining Two Paths
            System.out.format("resolve: %s%n", path.resolve("file.txt"));

            // Creating a Path Between Two Paths
            Path otherPath = Paths.get("resources/input.txt");
            System.out.format("relativize (to %s): %s%n", otherPath, path.relativize(otherPath));

            // Comparing Two Paths
            if (path.equals(otherPath)) {
                System.out.format("%s == %s%n", path, otherPath);
            } else {
                System.out.format("%s != %s%n", path, otherPath);
            }
            if (path.startsWith("resources")) {
                System.out.format("%s starts with \"resources\"%n", path);
            } else {
                System.out.format("%s does not start with \"resources\"%n", path);
            }
            if (path.endsWith("file.txt")) {
                System.out.format("%s ends with \"file.txt\"%n", path);
            } else {
                System.out.format("%s does not end with \"file.txt\"%n", path);
            }

            endTest();
        }
        { // File Operations
            // Releasing System Resources

            // Catching Exceptions

            // Varargs

            // Atomic Operations

            // Method Chaining

            // What Is a Glob?
            // *    Matches any number of characters (including none)
            // **   Works like * but crosses directory boundaries
            // ?    Matches exactly one character
            // {}   Specifies a collection of sub-pattern
            // []   Conveys a set of single characters or a range of characters
            // \    Match special characters (\\, \?)

            // Link Awareness
        }
        { // Checking a File or Directory
            startTest("TEST FILE (1)");

            Path file = Paths.get("resources/folder/file.txt");

            // Verifying the Existence of a File or Directory
            if (Files.exists(file)) {
                System.out.format("File %s exists%n", file);
            } else if (Files.notExists(file)) {
                System.out.format("File %s does not exist%n", file);
            } else {
                System.out.format("The existence of %s cannot be verified%n", file);
            }

            // Checking File Accessibility
            if (Files.isReadable(file)) {
                System.out.format("File %s is readable%n", file);
            }
            if (Files.isWritable(file)) {
                System.out.format("File %s is writable%n", file);
            }
            if (Files.isExecutable(file)) {
                System.out.format("File %s is executable%n", file);
            }

            // Checking Whether Two Paths Locate the Same File
            Path otherFile = Paths.get("resources/folder/file.txt");
            if (Files.isSameFile(file, otherFile)) {
                System.out.format("%s == %s%n", file, otherFile);
            } else {
                System.out.format("%s != %s%n", file, otherFile);
            }

            endTest();
        }
        { // Copying a File or Directory
            startTest("TEST FILE (2)");

            Path folder = Paths.get("resources/folder");
            Path newFolder = Paths.get("resources/new_folder");
            Files.copy(folder, newFolder, REPLACE_EXISTING);

            Path file = Paths.get("resources/folder/file.txt");
            Path newFile = Paths.get("resources/new_folder/new_file.txt");
            Files.copy(file, newFile, REPLACE_EXISTING, COPY_ATTRIBUTES);

            endTest();
        }
        { // Moving a File or Directory
            startTest("TEST FILE (3)");

            Path file = Paths.get("resources/new_folder/new_file.txt");
            Path newFile = Paths.get("resources/new_file.txt");
            Files.move(file, newFile, REPLACE_EXISTING, ATOMIC_MOVE);

            endTest();
        }
        { // Deleting a File or Directory
            startTest("TEST FILE (4)");

            Path file = Paths.get("resources/new_file.txt");
            try {
                Files.delete(file);
            } catch (NoSuchFileException exception) {
                System.err.format("%s: no such file or directory%n", file);
            } catch (DirectoryNotEmptyException exception) {
                System.err.format("%s not empty%n", file);
            } catch (IOException exception) {
                System.err.println(exception);
            }

            Path folder = Paths.get("resources/new_folder");
            try {
                Files.delete(folder);
            } catch (NoSuchFileException exception) {
                System.err.format("%s: no such file or directory%n", folder);
            } catch (DirectoryNotEmptyException exception) {
                System.err.format("%s not empty%n", folder);
            } catch (IOException exception) {
                System.err.println(exception);
            }

            endTest();
        }
        { // Managing Metadata
            startTest("TEST FILE (5)");

            Path file = Paths.get("resources/folder/file.txt");

            // Basic File Attributes
            BasicFileAttributes basicFileAttributes = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("creationTime: " + basicFileAttributes.creationTime());
            System.out.println("lastAccessTime: " + basicFileAttributes.lastAccessTime());
            System.out.println("lastModifiedTime: " + basicFileAttributes.lastModifiedTime());
            System.out.println("isDirectory: " + basicFileAttributes.isDirectory());
            System.out.println("isOther: " + basicFileAttributes.isOther());
            System.out.println("isRegularFile: " + basicFileAttributes.isRegularFile());
            System.out.println("isRegularFile: " + basicFileAttributes.isRegularFile());
            System.out.println("isSymbolicLink: " + basicFileAttributes.isSymbolicLink());
            System.out.println("size: " + basicFileAttributes.size());

            // Setting Time Stamps
            long currentTime = System.currentTimeMillis();
            FileTime fileTime = FileTime.fromMillis(currentTime);
            Files.setLastModifiedTime(file, fileTime);

            // DOS File Attributes
            try {
                DosFileAttributes dosFileAttributes = Files.readAttributes(file, DosFileAttributes.class);
                System.out.println("isReadOnly: " + dosFileAttributes.isReadOnly());
                System.out.println("isHidden: " + dosFileAttributes.isHidden());
                System.out.println("isArchive: " + dosFileAttributes.isArchive());
                System.out.println("isSystem: " + dosFileAttributes.isSystem());
            } catch (UnsupportedOperationException exception) {
                System.err.println("DOS file attributes not supported: " + exception);
            }

            // POSIX File Permissions
            try {
                PosixFileAttributes posixFileAttributes = Files.readAttributes(file, PosixFileAttributes.class);
                System.out.format("%s %s %s %s%n",
                        posixFileAttributes.owner().getName(),
                        posixFileAttributes.group().getName(),
                        PosixFilePermissions.toString(posixFileAttributes.permissions()));

                // Setting a File Owner
                UserPrincipal owner = file.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("chungphb");
                Files.setOwner(file, owner);

                // Setting a Group Owner
                GroupPrincipal group = file.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName("viettel");
                Files.getFileAttributeView(file, PosixFileAttributeView.class).setGroup(group);
            } catch (UnsupportedOperationException exception) {
                System.out.println("Posix file attributes not supported: " + exception);
            }

            // User-Defined File Attributes
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
            view.write("user.type", Charset.defaultCharset().encode("secret"));
            String name = "user.type";
            ByteBuffer buffer = ByteBuffer.allocate(view.size(name));
            view.read(name, buffer);
            buffer.flip();
            String value = Charset.defaultCharset().decode(buffer).toString();
            System.out.format("%s: %s%n", name, value);

            // File Store Attributes
            FileStore fileStore = Files.getFileStore(file);
            long total = fileStore.getTotalSpace() / 1024;
            long available = fileStore.getUsableSpace() / 1024;
            long used = (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / 1024;
            System.out.format("Total: %d%n", total);
            System.out.format("Available: %d%n", available);
            System.out.format("Used: %d%n", used);

            endTest();
        }
        { // Reading, Writing, and Creating Files

        }
        { // Random Access Files

        }
        { // Creating and Reading Directories

        }
        { // Links, Symbolic or Otherwise

        }
        { // Walking the File Tree

        }
        { // Finding Files

        }
        { // Watching a Directory for Changes

        }
        { // Other Useful Methods

        }
        { // Legacy File I/O Code

        }
    }

    public static void startTest(String resources) {
        System.out.println(resources);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
