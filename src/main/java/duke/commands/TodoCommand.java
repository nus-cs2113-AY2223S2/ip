package duke.commands;
import duke.tasks.Todo;
import duke.outputs.Messages;

public class TodoCommand extends TaskCommand{
    private String taskDescription;

    public TodoCommand(String taskDescription) {
        super("todo");
        this.taskDescription = taskDescription;
    }
    public CommandResult execute() {

        Todo task = new Todo(taskDescription);
        tasklist.addTask(task);
        return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                String.format("\n\t\t%s \n", task),
                String.format(Messages.MESSAGE_TASK_LIST_SIZE, tasklist.getNumberOfTasks()));

    }
}
