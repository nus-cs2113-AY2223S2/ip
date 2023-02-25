package duke.command;

import duke.tasklist.exception.DuplicateTaskException;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a todo task";

    private static final String REPLY_HEADER = "Got it. I've added this task: \n";
    private static final String REPLY_TAIL = "Now you have %d tasks in the list.";
    private final String content;

    public TodoCommand(String content) {
        this.content = content;
    }

    @Override
    public CommandResult execute() {
        Task task = new Todo(content);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult("This task is already added in the list.");
        }
        return new CommandResult(
                REPLY_HEADER, task + "\n", String.format(REPLY_TAIL, taskList.size())
        );
    }
}
