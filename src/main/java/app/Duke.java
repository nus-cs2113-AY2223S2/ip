package app;

import app.commands.Command;
import app.exceptions.DukeException;
import app.parser.MainParser;
import app.tasks.TaskList;
import app.ui.Ui;
import app.save.Storage;

/**
 * The main Duke program.
 * @author Mustafa Anis Hussain
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke that accesses memory to check for previously added Tasks.
     * @param filePath The path address for storage to memory.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to initialise Duke, and execute commands based on user
     * input until the exit condition is met.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = MainParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
