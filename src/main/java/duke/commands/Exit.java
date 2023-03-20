package duke.commands;

import duke.Storage;
import duke.TaskList;

public class Exit extends Command {

    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bye bye. Hope you have a great day");
    }
}

