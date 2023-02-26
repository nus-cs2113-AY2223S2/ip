package duke.command;

import java.time.LocalDate;

import duke.common.CommandReply;
import duke.data.exception.DuplicateTaskException;
import duke.data.task.Task;
import duke.data.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a deadline task with by time";

    private final String content;
    private final LocalDate byTime;

    public DeadlineCommand(String content, LocalDate byTime) {
        this.content = content;
        this.byTime = byTime;
    }

    @Override
    public CommandResult execute() {
        Task task = new Deadline(content, byTime);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult(CommandReply.ADD_TASK_DUPLICATE);
        }
        return new CommandResult(
                CommandReply.ADD_TASK_HEADER,
                task + "\n",
                String.format(CommandReply.ADD_TASK_TAIL_F, taskList.size())
        );
    }
}
