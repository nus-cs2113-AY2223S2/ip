package command;

import exception.DukeException;
import task.Task;

public abstract class Command {
    private final String[] commands;

    public Command(String[] commands) {
        this.commands = commands;
    }

    public String[] getCommands() {
        return commands;
    }

    public abstract void doCommand(int taskCount, Task[] tasks);
}
