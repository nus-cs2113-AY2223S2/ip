package command;

import jonathan.JonathanException;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public abstract class Command {

    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws JonathanException {
        return tasks;
    }

    public boolean isExit() {
        return false;
    }
}
