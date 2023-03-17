package duke.main;

import duke.save.Storage;
import duke.tasks.TaskList;

import java.io.IOException;

import static java.lang.System.exit;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @param filepath The filepath of the file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        try {
            storage.initFile();
        } catch (IOException e) {
            System.out.println("Error with hard disk!");
            exit(1);
        }

        storage.loadFile(tasks);
        ui.greet();
        ui.run(tasks, storage);
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("./src/data").run();
    }
}