package buddy.commands;
import buddy.tasks.TaskList;

public abstract class Command {
    public abstract void executeCommand(TaskList taskList, String input); // executes the commands differently based on the various types of commands
}
