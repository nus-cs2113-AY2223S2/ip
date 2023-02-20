package duke.main;

import duke.command.UserCommandManager;
import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.ParseCommand;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Last Modified: 20.2.23 1659
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;


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


    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        ui.greetUser();
        ui.showLine();
        UserCommandManager commandManager = new UserCommandManager();
        while (true) {
            try {
                String[] userCommand = ParseCommand.readInput();
                commandManager.handleCommands(userCommand, storage, tasks, ui);
                ui.showLine();
            } catch (DukeException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * Waits for user commands from the command line and executes them
     *
     * @param args None taken
     */
    public static void main(String[] args) {
        new Duke("duke.json").run();
    }
}
