package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class IllegalCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.illegalCommandMessage();
    }
}
