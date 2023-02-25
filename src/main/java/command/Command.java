package command;

import task.Task;
import task.TaskList;
import storage.TaskStorage;
import ui.UI;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute (TaskList tasks, TaskStorage storage, UI ui);
}
