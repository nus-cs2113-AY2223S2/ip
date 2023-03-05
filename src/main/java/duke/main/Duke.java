package duke.main;

import duke.entity.Storage;
import duke.entity.TaskList;
import duke.entity.Ui;
import duke.exceptions.DukeException;

/**
 * Contains the main method where it begins the program and runs the program as the base of the
 * Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor initiates the instance variables given the loaded tasks from the data file
     *
     * @param filePath string of the file path where tasks data will be saved
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the program after initializing Duke. Loops through manageInput method which takes in the input and
     * outputs results to user until they exit the program
     */
    public void run() {
        ui.greetings();
        boolean exited = true;
        while (exited) {
            exited = ui.manageInput(tasks, storage);
        }
        ui.goodbye();
    }

    /**
     * Initializes the instance variable Duke duke and runs the program
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}