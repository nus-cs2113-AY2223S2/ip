package duke.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class provides methods to read, write, and update text files containing Task objects.
 * It uses the file path and a list of Task objects to perform file operations.
 */
public class Storage {

    /** The file path of the text file to be read, written, or updated */
    protected static String filepath;

    /** The list of Task objects to be stored in the text file */
    protected ArrayList<Task> listOfTask;

    /**
     * Constructs a Storage object with the specified file path and list of Task objects.
     *
     * @param filepath the file path of the text file to be read, written, or updated
     * @param listOfTask the list of Task objects to be stored in the text file
     */
    public Storage(String filepath, ArrayList<Task> listOfTask) {
        this.filepath = filepath;
        this.listOfTask = listOfTask;
    }

    /**
     * Prints the contents of the text file with the specified file path to the console.
     *
     * @param filePath the file path of the text file to be printed
     * @throws FileNotFoundException if the file with the specified file path is not found
     */
    public static void printFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

    /**
     * Writes the specified text to the text file with the specified file path.
     *
     * @param filePath the file path of the text file to be written
     * @param textAdd the text to be added to the text file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void writeFile(String filePath, String textAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textAdd);
        writer.close();
    }

    /**
     * Updates the contents of the text file with the specified file path with the Task objects in the specified list.
     *
     * @param filePath the file path of the text file to be updated
     * @param listOfTask the list of Task objects to be stored in the text file
     * @throws IOException if an I/O error occurs while updating the file
     */
    public static void updateFile(String filePath, ArrayList<Task> listOfTask) throws IOException {
        String newList = "";
        for (int i = 0; i < listOfTask.size(); i++) {
            newList += listOfTask.get(i).toString() + System.lineSeparator();
        }
        writeFile(filePath, newList);
    }
}
