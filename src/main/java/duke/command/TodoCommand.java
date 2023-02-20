package duke.command;

import duke.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Todo(taskName));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
