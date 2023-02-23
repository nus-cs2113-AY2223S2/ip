package duke.main;


import java.util.ArrayList;
import java.io.IOException;

import duke.Storage.Storage;
import duke.tasks.*;

import static java.lang.System.exit;

/**
 * The main executable application
 */
public class Duke {
    static final int limitTask = 100;
    static ArrayList<Task> tasksList = new ArrayList<>();
    public static int taskCount = 0;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * A <code>Duke</code> object initiate the Ui , Storage and taskslist
     *
     * @param filepath relaive path of where the tasks list is stored
     */

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Run the Duke app
     *
     * @throws IOException if file crash during file initiation or loading
     */

    public void run() throws IOException {
        try {
            storage.initFile();
            storage.loadFile(tasks);
        } catch (IOException e) {
            System.out.println("I/O Error! ");
            exit(1);
        }

        ui.greet();
        ui.run(tasks, storage);
        ui.bye();

    }

    /**
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        new Duke("./taskslist.csv").run();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
