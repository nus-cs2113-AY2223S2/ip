package Alex.command;

import Alex.task.TaskList;

public class IncorrectCommand extends Command{

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult("Invalid command! Please" +
                "enter a valid command");

    }

}
