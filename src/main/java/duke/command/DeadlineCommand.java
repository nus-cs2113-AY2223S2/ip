package duke.command;

import duke.tasklist.exception.DuplicateTaskException;
import duke.tasklist.task.Task;
import duke.tasklist.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a deadline task with by time";

    private static final String REPLY_HEADER = "Got it. I've added this task: \n";
    private static final String REPLY_TAIL_FORMAT = "Now you have %d tasks in the list.";
    private static final String REPLY_DUPLICATE = "This task is already added in the list.";

    private final String content;
    private final String byTime;

    public DeadlineCommand(String content, String byTime) {
        this.content = content;
        this.byTime = byTime;
    }

    @Override
    public CommandResult execute() {
        Task task = new Deadline(content, byTime);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult(REPLY_DUPLICATE);
        }
        return new CommandResult(
                REPLY_HEADER, task + "\n", String.format(REPLY_TAIL_FORMAT, taskList.size())
        );
    }
}
