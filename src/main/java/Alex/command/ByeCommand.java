package Alex.command;

import Alex.task.TaskList;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Bye. Hope to chat with you again soon!";

    /**
     * Executes the command and returns the result.
     *
     * @return CommandResult which is a exit message to user
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(MESSAGE_EXIT);
    }

    /**
     * Check if a ByeCommand is executed and user wants to exit program
     *
     * @param command user command
     * @return true if user wants to exit false if not
     */
    public static boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }
}
