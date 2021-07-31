package com.io.test_file_io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Arrays;
import java.util.EnumSet;

import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.FileVisitResult.*;

class Printer extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        if (attributes.isRegularFile()) {
            System.out.format("File: %s ", file);
        } else if (attributes.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attributes.size() / 1024 + "K)");
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exception) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exception) {
        System.err.println(exception);
        return CONTINUE;
    }
}

class Finder extends SimpleFileVisitor<Path> {
    private final PathMatcher matcher;
    private int nMatches = 0;

    Finder(String pattern) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
    }

    void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            nMatches++;
            System.out.println(file);
        }
    }

    void done() {
        System.out.println("Matched: " + nMatches);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        find(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exception) {
        System.err.println(exception);
        return CONTINUE;
    }
}

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
            Path path = Paths.get("./resources/test");

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
            System.out.format("resolve: %s%n", path.resolve("data.txt"));

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
            if (path.endsWith("data.txt")) {
                System.out.format("%s ends with \"data.txt\"%n", path);
            } else {
                System.out.format("%s does not end with \"data.txt\"%n", path);
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

            Path file = Paths.get("resources/test/data.txt");

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
            Path otherFile = Paths.get("resources/test/data.txt");
            if (Files.isSameFile(file, otherFile)) {
                System.out.format("%s == %s%n", file, otherFile);
            } else {
                System.out.format("%s != %s%n", file, otherFile);
            }

            endTest();
        }
        { // Copying a File or Directory
            startTest("TEST FILE (2)");

            Path dir = Paths.get("resources/test");
            Path newdir = Paths.get("resources/new_dir");
            Files.copy(dir, newdir, REPLACE_EXISTING);

            Path file = Paths.get("resources/test/data.txt");
            Path newFile = Paths.get("resources/new_dir/new_file.txt");
            Files.copy(file, newFile, REPLACE_EXISTING, COPY_ATTRIBUTES);

            endTest();
        }
        { // Moving a File or Directory
            startTest("TEST FILE (3)");

            Path file = Paths.get("resources/new_dir/new_file.txt");
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

            Path dir = Paths.get("resources/new_dir");
            try {
                Files.delete(dir);
            } catch (NoSuchFileException exception) {
                System.err.format("%s: no such file or directory%n", dir);
            } catch (DirectoryNotEmptyException exception) {
                System.err.format("%s not empty%n", dir);
            } catch (IOException exception) {
                System.err.println(exception);
            }

            endTest();
        }
        { // Managing Metadata
            startTest("TEST FILE (5)");

            Path file = Paths.get("resources/test/data.txt");

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
                System.err.println("Posix file attributes not supported: " + exception);
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
            startTest("TEST FILE (6)");

            { // The OpenOptions Parameter
                // WRITE                Opens the file for write access
                // APPEND               Appends the new data to the end of the file
                // TRUNCATE_EXISTING    Truncates the file to zero bytes
                // CREATE_NEW           Creates a new file
                // CREATE               Opens the file if it exists or creates a new file
                // DELETE_ON_CLOSE      Deletes the file when the stream is closed
                // SPARSE               Hints that a newly created file will be sparse
                // SYNC                 Keeps the file (both content & metadata) synchronized with the underlying storage device
                // DSYNC                Keeps the file content synchronized with the underlying storage device
            }
            { // Commonly Used Methods For Small Files
                Path file = Paths.get("out/data.txt");

                // Writing All Bytes or Lines to a File
                byte[] data = {0x54, 0x69, 0x65, 0x6e, 0x4c, 0x58};
                Files.write(file, data);

                // Reading All Bytes or Lines from a File
                byte[] content = Files.readAllBytes(file);

                if (!Arrays.equals(data, content)) {
                    System.err.println("Something went wrong");
                }
            }
            { // Buffered I/O Methods for Text Files
                Path file = Paths.get("out/data.txt");

                // Writing a File by Using Buffered Stream I/O
                Charset charset = StandardCharsets.US_ASCII;
                String data = "Time makes us sentimental. Perhaps, in the end, it is because of time that we suffer.";
                try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                    writer.write(data, 0, data.length());
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }

                // Reading a File by Using Buffered Stream I/O
                try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }
            }
            { // Methods for Unbuffered Streams and Interoperable with java.io APIs
                Path file = Paths.get("out/data.txt");

                // Creating and Writing a File by Using Stream I/O
                String str = "We are not written for one instrument alone; I am not, neither are you.";
                byte[] data = str.getBytes();
                try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(file))) {
                    outputStream.write(data, 0, data.length);
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }

                // Reading a File by Using Stream I/O
                try (InputStream inputStream = Files.newInputStream(file)) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }
            }
            { // Methods for Channels and ByteBuffers
                Path file = Paths.get("out/data.txt");

                // Writing Files by Using Channel I/O
                String str = "We belonged to each other but had lived so far apart that we belonged to others now.";
                byte[] data = str.getBytes();
                ByteBuffer byteBuffer = ByteBuffer.wrap(data);
                try (SeekableByteChannel byteChannel = Files.newByteChannel(file, READ, WRITE)) {
                    byteChannel.write(byteBuffer);
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }

                // Reading Files by Using Channel I/O
                try (SeekableByteChannel byteChannel = Files.newByteChannel(file)) {
                    final int BUFFER_CAPACITY = 40;
                    byteBuffer = ByteBuffer.allocate(BUFFER_CAPACITY);
                    String encoding = "US-ASCII";
                    while (byteChannel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        System.out.println(Charset.forName(encoding).decode(byteBuffer));
                        byteBuffer.clear();
                    }
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }
            }
            { // Methods for Creating Regular and Temporary Files
                // Creating Files
                Path file = Paths.get("out/sample.txt");
                try {
                    Files.createFile(file);
                } catch (FileAlreadyExistsException exception) {
                    System.err.format("File %s already exists%n", file);
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }

                // Creating Temporary Files
                try {
                    Path tempFile = Files.createTempFile(null, ".temp");
                    System.out.format("The temporary file has been created: %s%n", tempFile);
                } catch (IOException exception) {
                    System.err.format("IOException: %s%n", exception);
                }
            }
            endTest();
        }
        { // Random Access Files
            startTest("TEST FILE (7)");

            Path file = Paths.get("out/data.txt");
            String str = "We loved each other";
            byte[] data = str.getBytes();
            ByteBuffer outBuffer = ByteBuffer.wrap(data);
            ByteBuffer inBuffer = ByteBuffer.allocate(25);
            try (FileChannel fileChannel = (FileChannel.open(file, READ, WRITE))) {
                int nReads;
                do {
                    nReads = fileChannel.read(inBuffer);
                } while (nReads != -1 && inBuffer.hasRemaining());
                inBuffer.flip();
                System.out.println(StandardCharsets.US_ASCII.decode(inBuffer));

                fileChannel.position(0);
                while (outBuffer.hasRemaining()) {
                    fileChannel.write(outBuffer);
                }
            } catch (IOException exception) {
                System.err.format("IOException: %s%n", exception);
            }

            endTest();
        }
        { // Creating and Reading Directories
            startTest("TEST FILE (8)");

            { // Listing a File System's Root Directories
                System.out.println("Root directories:");
                Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
                for (Path dir : dirs) {
                    System.out.format("\t%s%n", dir);
                }
            }
            { // Creating a Directory
                Path dir = Paths.get("out/test");
                Files.createDirectory(dir);
            }
            { // Creating a Temporary Directory
                Path tempDir = Paths.get("out/test");
                Files.createTempDirectory(tempDir, "tmp_");
            }
            { // Listing a Directory's Contents
                Path dir = Paths.get("out");
                System.out.format("%s's content:%n", dir);
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
                    for (Path file : dirStream) {
                        System.out.format("\t%s%n", file);
                    }
                } catch (IOException | DirectoryIteratorException exception) {
                    System.err.println(exception);
                }
            }
            { // Filtering a Directory Listing By Using Globbing
                Path dir = Paths.get("out");
                System.out.format("%s's content (after filtering with globbing):%n", dir);
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, "*.{txt}")) {
                    for (Path file : dirStream) {
                        System.out.format("\t%s%n", file);
                    }
                } catch (IOException | DirectoryIteratorException exception) {
                    System.err.println(exception);
                }
            }
            { // Writing Directory Filter
                DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path path) throws IOException {
                        return Files.isDirectory(path);
                    }
                };
                Path dir = Paths.get("out");
                System.out.format("%s's content (after filtering with a custom filter):%n", dir);
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, filter)) {
                    for (Path file : dirStream) {
                        System.out.format("\t%s%n", file);
                    }
                } catch (IOException | DirectoryIteratorException exception) {
                    System.err.println(exception);
                }
            }
            
            endTest();
        }
        { // Links, Symbolic or Otherwise
            startTest("TEST FILE (9)");

            { // Creating a Symbolic Link
                Path link = Paths.get("out/slink.txt");
                Path target = Paths.get("resources/test/data.txt");
                try {
                    Files.createSymbolicLink(link, target);
                } catch (IOException | UnsupportedOperationException exception) {
                    System.err.println(exception);
                }
            }
            { // Creating a Hard Link
                Path link = Paths.get("out/link.txt");
                Path target = Paths.get("resources/test/data.txt");
                try {
                    Files.createLink(link, target);
                } catch (IOException | UnsupportedOperationException exception) {
                    System.err.println(exception);
                }
            }
            { // Detecting a Symbolic Link
                Path path = Paths.get("out/slink.txt");
                System.out.format("Is %s a symbolic link: %b%n", path, Files.isSymbolicLink(path));
            }
            { // Finding the Target of a Link
                Path path = Paths.get("out/slink.txt");
                try {
                    if (Files.isSymbolicLink(path)) {
                        System.out.format("Target of link %s is %s%n", path, Files.readSymbolicLink(path));
                    }
                } catch (IOException exception) {
                    System.err.println(exception);
                }
            }

            endTest();
        }
        { // Walking the File Tree
            startTest("TEST FILE (10)");

            Path startingDir = Paths.get("resources");
            EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
            Printer printer = new Printer();
            Files.walkFileTree(startingDir, options, Integer.MAX_VALUE, printer);

            endTest();
        }
        { // Finding Files
            startTest("TEST FILE (11)");

            Path startingDir = Paths.get("resources");
            Finder finder = new Finder("*.txt");
            Files.walkFileTree(startingDir, finder);
            finder.done();

            endTest();
        }
        { // Watching a Directory for Changes

        }
        { // Other Useful Methods
            startTest("TEST FILE (12)");

            { // Determining MIME Type
                Path file = Paths.get("resources/test/data.txt");
                try {
                    String type = Files.probeContentType(file);
                    if (type == null) {
                        System.out.format("%s has an unknown file type%n", file);
                    } else if (type.equals("text/plain")) {
                        System.out.format("%s is a plain text file%n", file);
                    } else {
                        System.out.format("%s is not a plain text file%n", file);
                    }
                } catch (IOException exception) {
                    System.err.println(exception);
                }
            }
            { // Default File System / Path String Separator
                System.out.println("Root directories:");
                Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
                for (Path dir : dirs) {
                    System.out.format("\t%s%n", dir);
                }
                System.out.println("Separator: " + FileSystems.getDefault().getSeparator());
            }
            { // File System's File Stores
                for (FileStore fileStore : FileSystems.getDefault().getFileStores()) {
                    long total = fileStore.getTotalSpace() / 1024;
                    long available = fileStore.getUsableSpace() / 1024;
                    long used = (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / 1024;
                    System.out.format("File store: %s%n", fileStore.name());
                    System.out.format("\tTotal: %d%n", total);
                    System.out.format("\tAvailable: %d%n", available);
                    System.out.format("\tUsed: %d%n", used);
                }
            }

            endTest();
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
