package duke.command;

import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Todo(taskName));
        String output = giveAddMessage();
        Task.incrementTotalTasks();
        return new CommandResult(output);
    }
}
