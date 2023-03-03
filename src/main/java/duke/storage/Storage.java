package duke.storage;

import duke.ui.Ui;

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
            Ui.printMessage(Ui.CommandType.FILENOTFOUND);
        } catch (RuntimeException e) {
            Ui.printMessage(Ui.CommandType.RUNTIMEEXCEPTION);
        }
    }

    /**
     * Write list of tasks to existing txt file.
     */
    public static void write() {
        try {
            FileReading.writeToFile();
        } catch (IOException e) {
            Ui.printMessage(Ui.CommandType.IOEXCEPTION);
        }
    }
}
