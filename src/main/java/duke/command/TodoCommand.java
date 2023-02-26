package duke.command;

import duke.tasklist.exception.DuplicateTaskException;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;
import duke.common.AddTaskCommandReply;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a todo task";

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
                AddTaskCommandReply.REPLY_HEADER,
                task + "\n",
                String.format(AddTaskCommandReply.REPLY_TAIL_FORMAT, taskList.size())
        );
    }
}
