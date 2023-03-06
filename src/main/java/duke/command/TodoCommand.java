package duke.command;

import duke.task.Todo;

/**
 * Adds a todo task
 */
public class TodoCommand extends AddCommand {

    public static final String INVALID_COMMAND_MESSAGE = " Invalid input! Valid input format: \"todo <task name>\"";

    public TodoCommand(String taskName) {
        super(taskName);
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        taskList.addTask(new Todo(taskName));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
