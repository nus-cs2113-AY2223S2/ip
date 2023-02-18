package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.AddTaskException;
import luke.task.StringManipulation;

public abstract class Command implements StringManipulation {


    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
