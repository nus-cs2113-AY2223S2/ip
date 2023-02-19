package duke;

import duke.command.ExitCommand;
import duke.data.Storage;
import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.makeDirectory();
            File dataFile = storage.openDataFile();
            taskList = new TaskList(storage.importData(dataFile));
        } catch (Exception e) {
            // error from storage issues, exit
            Ui.showStartingError();
            System.exit(1);
        }
    }

    public void run() {
        Ui.showGreeting();
        runCommand();
        ExitCommand.exit(this.taskList);
    }

    public void runCommand() {
        String input = ui.getUserCommand();
        while (!input.equals(ExitCommand.COMMAND_WORD)) {
            Command.evaluateCommand(input, this.taskList);
            input = ui.getUserCommand();
        }
    }

    public static void main(String[] args) {
        new Duke(Storage.FILE_PATH).run();
    }
}