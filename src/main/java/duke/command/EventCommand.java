package duke.command;

import java.time.LocalDate;

import duke.data.exception.DuplicateTaskException;
import duke.data.task.Task;
import duke.data.task.Event;
import duke.common.CommandReply;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add an event task with start and to time "
            + "Example: event eat dinner /from 2023-01-01 /to 2024-01-01";
    private final String content;
    private final LocalDate fromTime;
    private final LocalDate toTime;

    public EventCommand (String content, LocalDate from, LocalDate to) {
        this.content = content;
        this.fromTime = from;
        this.toTime = to;
    }

    @Override
    public CommandResult execute() {
        Task task = new Event(content, fromTime, toTime);
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
