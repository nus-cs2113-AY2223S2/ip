package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IllegalCommandException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.goodbyeMessage();
        isExit = true;
    }


}
