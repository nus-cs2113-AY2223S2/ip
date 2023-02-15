package data;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import commandHandler.Add;
import ui.Display;

public class ProcessSavedTasks {
    public static final String FILE_PATH = "savedTasks.txt";

    public static void processFile() {
        try {
            File f = new File(FILE_PATH);
            if (!f.createNewFile()) {
                addTasks();
            }
        } catch (IOException e) {
            Display.warnUser("Error accessing file!");
        }
    }

    public static void addTasks() {
        try (Scanner s = new Scanner(new File(FILE_PATH))) {
            while (s.hasNextLine()) {
                String task = s.nextLine();
                Add.addSavedTask(task);
            }
        } catch (IOException e) {
            Display.warnUser("Error accessing file!");
        }

    }
}
