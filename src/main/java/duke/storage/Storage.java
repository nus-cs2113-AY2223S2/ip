package duke.storage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the class that deals with loading tasks from
 * the file and saving tasks into the file.
 */
public class Storage {
    /**
     * Gets list of tasks from existing txt file.
     */
    public static void load() {
        try {
            FileReading.getFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find what you want :(");
        }
    }

    /**
     * Write list of tasks to existing txt file.
     */
    public static void write() {
        try {
            FileReading.writeToFile();
        } catch (IOException e) {
            System.out.println("This is out of my ability to execute...");
        }
    }
}
