package Alex.command;

import Alex.task.TaskList;

public class IncorrectCommand extends Command{

    /**
     * Execute the command and returns default result when command is invalid
     *
     * @param taskList the taskList that contains all tasks of the user
     * @return CommandResult that will print to user that command entered is invalid
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult("Invalid command! Please" +
                "enter a valid command");

    }

}
