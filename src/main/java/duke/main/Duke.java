package duke.main;

import duke.command.UserCommandManager;
import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.GreetUser;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Last Modified: 20.2.23 1659
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskData tasks;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskData(storage.setTasks());
        } catch (DukeException e) {
            ui.errorMessage(e.getMessage());
            tasks = new TaskData();
        }
    }


    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        GreetUser.greetUser();
        Scanner input = new Scanner(System.in);
        UserCommandManager commandManager = new UserCommandManager();
        while (true) {
            try {
                String[] userCommand = new String[2];
                userCommand[0] = input.next().toLowerCase();
                userCommand[1] = input.nextLine();
                commandManager.handleCommands(userCommand, storage, tasks);
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
