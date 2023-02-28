package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;
public abstract class Command {
    public abstract void executeCommand(String[] input, TaskList tasks, Ui ui);
}


