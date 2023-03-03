import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents file operations that will allow for
 * making, adding and appending to file
 */
public class FileOperations {
    /**
     * @param path Path location where file should be saved
     * @param name Name of file created
     * @throws IOException when failure to create a file
     */
    public static void makeFile (String path, String name) throws IOException {
        File directory, file;
        directory = new File(name);
        file = new File(path);

        // if dir does not exist, make it
        if (!directory.exists()){
            directory.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException exception) {
            System.out.println("An error occurred when creating file");
        }
    }

    /**
     * Append to file with data provided by user
     * @param path Location of file
     * @param data Data that will be appended
     *             to file
     * @throws IOException when unable to append
     * to file
     */
    public static void appendFile(String path, String data) throws IOException {
        FileWriter add;
        add = new FileWriter(path, true);
        add.write(data);
        add.close();
    }

    /**
     * Add to file with data provided by user
     * @param path Location of file
     * @param data Data that will be added
     *        to file
     * @throws IOException when unable to add
     * to file
     */
    public static void addToFile(String path, String data) throws IOException {
        FileWriter add;
        add = new FileWriter(path);
        add.write(data);
        add.close();
    }
}