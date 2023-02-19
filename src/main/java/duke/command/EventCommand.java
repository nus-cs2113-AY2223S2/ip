package duke.command;

import duke.task.Event;
import duke.task.Task;

public class EventCommand extends AddCommand {
    public static String startDate;
    public static String endDate;

    public EventCommand(String taskName, String startDate, String endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Event(taskName, startDate, endDate));
        String output = giveAddMessage();
        Task.incrementTotalTasks();
        return new CommandResult(output);
    }
}
