package duke;

import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.data.Storage;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;

public class Duke {
    private final Ui ui;
    private final Storage storage;
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
            ui.showStartingError();
            System.exit(1);
        }
    }

    public void run() {
        ui.greetingMessage();
        runCommand();
        ExitCommand.exit(this.taskList, this.ui, this.storage);
    }

    public void runCommand() {
        Command command;
        String input = ui.getUserCommand();
        while (!input.equals(ExitCommand.COMMAND_WORD)) {
            command = new Parser().parseCommand(input);
            try {
                CommandResult outcome = executeCommand(command);
                ui.showToUser(outcome.output);
                input = ui.getUserCommand();
            } catch (NullPointerException e) {
                input = ui.getUserCommand();
            }
        }
    }

    private CommandResult executeCommand(Command command) {
        command.setData(taskList);
        return command.execute();
    }

    public static void main(String[] args) {
        new Duke(Storage.FILE_PATH).run();
    }
}