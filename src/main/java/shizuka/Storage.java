package shizuka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String ITEM_SEPARATOR = " / ";
    private static final String DEFAULT_PATH = "shizuka.txt";

    /**
     * Saves the current list of tasks to a specific file.
     *
     * @param filePath The path of the file to save to.
     * @param text     The list of tasks to save.
     * @throws IOException If the file cannot be created.
     */
    static void save(String filePath, String[] text) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        if (f.createNewFile()) {
            UI.fileCreated();
        } else {
            UI.fileExists();
        }
        for (String item : text) {
            fw.write(item);
        }
        fw.close();
    }

    /**
     * Saves the current list of tasks to the default file.
     *
     * @param text The list of tasks to save.
     * @throws IOException If the file cannot be created.
     */
    static void save(String[] text) throws IOException {
        save(DEFAULT_PATH, text);
    }

    /**
     * Loads the list of tasks from a file.
     *
     * @param filePath The path of the file to load from.
     * @param list     The list of tasks to load into.
     * @throws IOException If the file cannot be found.
     */
    static void load(String filePath, TodoList list) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] args = s.nextLine().split(ITEM_SEPARATOR);
            switch (args[0]) {
            case "T":
                list.addTodoFromFile(args);
                break;
            case "D":
                list.addDeadlineFromFile(args);
                break;
            case "E":
                list.addEventFromFile(args);
                break;
            }
        }
    }

    /**
     * Loads the list of tasks from the default file.
     *
     * @param list The list of tasks to load into.
     * @throws IOException If the file cannot be found.
     */
    static void load(TodoList list) throws IOException {
        load(DEFAULT_PATH, list);
    }

}