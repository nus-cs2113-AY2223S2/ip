package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The Duke class represents the main class of the Duke application.
 * It handles the creation of the TaskList, Storage and TextUi objects and runs the application by taking in user input
 * and executing the corresponding command parsed by the Parser class.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Constructor for the Duke class.
     * Creates a new TextUi and Storage object and initializes the TaskList object with data read from the file specified
     * in the filePath parameter.
     * If the file does not exist, the TaskList object is initialized with an empty list of tasks and an error message is displayed.
     *
     * @param filePath The path of the file to read data from.
     */
    public Duke(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application by displaying an introduction message and continuously taking in user input
     * until the user enters the exit command.
     * The user input is parsed by the Parser class and the corresponding Command object is executed.
     * The current state of the TaskList is saved to the file specified in the Duke constructor after each command execution.
     */
    public void run() {
        ui.showIntroduction();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.scanLine();
            Command c = Parser.parseCommand(fullCommand);
            c.execute(tasks, ui, storage);

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            isExit = c.isExit();
        }
    }

    /**
     * Main method for the Duke application.
     * Creates a new Duke object with the specified file path and runs the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
