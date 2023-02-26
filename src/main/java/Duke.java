import duke.exception.DukeException;

import duke.functionalities.Command;
import duke.functionalities.Storage;
import duke.functionalities.TaskList;
import duke.functionalities.Parser;
import duke.functionalities.Ui;

public class Duke {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;

    /**
     * Loads the Stored Data into the Duke Task List
     *
     * @param filePath The Path of Duke Data File
     * */
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath, UI);
        try {
            UI.showLine();
            tasks = new TaskList(STORAGE.load(), UI);
            UI.showLine();
        } catch (DukeException e) {
            UI.showLoadingError();
        }
    }

    public static void main(String[] args) {
        new Duke("DukeData.txt").run();
    }

    /**
     * This method executes the Duke Task Operations
     * */
    public void run() {
        UI.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readUserCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeDukeCommands(tasks, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException | NullPointerException e) {
                UI.showErrorMessage();
            } finally {
                UI.showLine();
            }
        }
    }
}