package duke.command;

import duke.tasklist.exception.DuplicateTaskException;
import duke.tasklist.task.Task;
import duke.tasklist.task.Event;
import duke.common.AddTaskCommandReply;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add an event task with start and to time";
    private final String content;
    private final String from;
    private final String to;

    public EventCommand (String content, String from, String to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute() {
        Task task = new Event(content, from, to);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult(AddTaskCommandReply.REPLY_DUPLICATE);
        }
        return new CommandResult(
                AddTaskCommandReply.REPLY_HEADER,
                task + "\n",
                String.format(AddTaskCommandReply.REPLY_TAIL_FORMAT, taskList.size())
        );
    }
}
