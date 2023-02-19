package duke;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents file operations such as writing and appending to a file.
 */
public class FileOperations {
    /**
     * Writes a specific string to the file specified by the file path.
     *
     * @param filePath The file path which specifies which file to write to.
     * @param textToAdd The string that is to be written into the file
     * @throws IOException if the file specified by the file path is not found.
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends a specific string to the file specified by the file path.
     *
     * @param filePath The file path which specifies which file to append to.
     * @param textToAppend The string that is to be appended into the file
     * @throws IOException if the file specified by the file path is not found.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

}