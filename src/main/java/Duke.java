import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to manage all operations
 */
public class Duke {
    public static boolean isEnd = false;
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Start the duke programme
     * @param args
     * @throws IOException if an error occurred while loading save file
     */
    public static void main(String[] args) throws IOException {
        new Duke("./taskSave.txt").run();
    }

    /**
     * Constructor to create new UI, storage and task list objects
     * @param filepath path of the previous save file
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Loads the save file and executes duke programme
     * @throws IOException if an error occurred while loading the save file
     */
    public void run() throws IOException {
        try {
            storage.createSaveFile();
        } catch (IOException exception) {
            System.out.println("Error Loading file!");
        }
        storage.loadSaveFile(tasks);
        ui.printHelloStatement();
        while (!isEnd) {
            String input = ui.getInput();
            Parser parser = new Parser(input);
            parser.handleCommand(tasks, ui);
            storage.updateSaveFile(tasks);
        }
    }
}