package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {

    private Boolean isExit = false;
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public void setExit() {
        isExit = true;
    }

    public Boolean getExit() {
        return isExit;
    }
}
