package inu.commands;

import inu.task.TaskList;

public abstract class Command {

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public abstract CommandResult execute(TaskList taskList);

}
