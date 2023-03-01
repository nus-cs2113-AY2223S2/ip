package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import commandHandler.Add;
import ui.Display;

/**
 * Loads storage tasks into Duke program.
 */

public class ProcessStorageTasks {
    public static final String HOME = System.getProperty("user.dir");
    public static final Path DIRECTORY = Paths.get(HOME, "storage");
    public static final Path FILE = Paths.get(HOME, "storage", "savedTasks.txt");
    public static final String FILE_PATH = FILE.toString();

    /*
     * Prepares storage .txt file to be read/edited by Duke program. Creates new
     * .txt file if doesn't already exists.
     */
    public static void processFile() {
        File directory = new File(DIRECTORY.toString());
        try {
            if (!directory.exists()) {
                Display.notifyUser("Initialising program...");
                directory.mkdirs();
            }
        } catch (Exception e) {
            Display.warnUser(e.getMessage());
        }

        try {
            File f = new File(FILE_PATH);
            if (!f.createNewFile()) {
                addTasks();
            }
        } catch (IOException e) {
            System.out.println("Path: " + FILE_PATH);
            Display.warnUser(e.getMessage());
        }
        Display.notifyUser("Program initialised. How may I help you?");
    }

    /**
     * Loads existing tasks stored in savedTasks.txt file into Duke program task
     * list.
     */
    public static void addTasks() {
        try (Scanner s = new Scanner(new File(FILE_PATH))) {
            while (s.hasNextLine()) {
                String task = s.nextLine();
                Add.addSavedTask(task);
            }
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }

    }
}
