package king.commands;

import king.exceptions.NoTasksException;

public abstract class Command {
    public abstract void handleCommand() throws NoTasksException;
}
