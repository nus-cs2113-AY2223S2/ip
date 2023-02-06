package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    private String errorMessage;
    private String command;

    public InvalidCommand(String errorMessage, String command) {
        this.errorMessage = errorMessage;
        this.command = command;
    }

    @Override
    public void run(TaskList taskList) {
        Ui.printError(errorMessage, command);
    }
}
