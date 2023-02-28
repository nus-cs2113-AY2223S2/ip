package Alex.command;

import Alex.task.TaskList;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Bye. Hope to chat with you again soon!";

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(MESSAGE_EXIT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}
