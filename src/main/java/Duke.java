import duke.exception.DukeException;

import duke.functionalities.Command;
import duke.functionalities.Storage;
import duke.functionalities.TaskList;
import duke.functionalities.Parser;
import duke.functionalities.Ui;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            ui.showLine();
            tasks = new TaskList(storage.load(), ui);
            ui.showLine();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public static void main(String[] args) {
        new Duke("DukeData.txt").run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeDukeCommands(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | NullPointerException e) {
                ui.showErrorMessage();
            } finally {
                ui.showLine();
            }
        }
    }
}