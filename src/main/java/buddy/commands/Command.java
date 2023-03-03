package buddy.commands;
import buddy.tasks.*;

public abstract class Command {
    public abstract void executeCommand(TaskList taskList, String input); // executes the commands differently based on the various types of commands
}
