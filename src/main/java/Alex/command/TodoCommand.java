package Alex.command;

import Alex.task.Task;
import Alex.task.TaskList;
import Alex.task.Todo;

public class TodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }
    @Override
    public CommandResult execute(TaskList taskList) {
        Task t = new Todo(description, COMMAND_WORD.substring(0,1).toUpperCase());
        taskList.setTask(t);
        return new CommandResult("Got it. I've added this task:" + "\n " + t + "\n" +
                "Now you have " + taskList.getNumberOfTasks() + " tasks in the list");
    };
}
