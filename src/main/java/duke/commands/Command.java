package duke.commands;

import duke.exceptions.NoTasksException;

public abstract class Command {
    public abstract void handleCommand() throws NoTasksException;
}
