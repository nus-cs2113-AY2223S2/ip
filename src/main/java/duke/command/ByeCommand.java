package duke.command;

import duke.TaskList;
import duke.UI;
import duke.Storage;


public class ByeCommand extends Command {

    public ByeCommand() {

    }

    public void execute(TaskList tasks, Storage storage) {
        UI.printBye();
    }
}
