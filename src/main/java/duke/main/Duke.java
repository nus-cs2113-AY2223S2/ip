package duke.main;

import duke.command.UserCommandManager;
import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.ReadUserInput;
import duke.ui.Ui;


/**
 * Main class to handle duke operations
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private static final String FILE_PATH = "duke.json";


    /**
     * Constructs the new Ui and storage handler and sets the tasklist from memory.
     *
     * @param filePath File directory where json file is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.setTasks());
        } catch (DukeException e) {
            ui.errorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Executes the program to poll for userInput and handles them.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Ui.greetUser();
        ui.showLine();
        UserCommandManager commandManager = new UserCommandManager();
        while (true) {
            try {
                String[] userCommand = ReadUserInput.readInput();
                commandManager.handleCommands(userCommand, storage, tasks, ui);
                ui.showLine();
            } catch (DukeException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * Start the program.
     *
     * @param args None taken.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
