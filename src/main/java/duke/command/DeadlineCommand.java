package duke.command;

import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends AddCommand {
    public static String deadline;
    public DeadlineCommand(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Deadline(taskName, deadline));
        String output = giveAddMessage();
        Task.incrementTotalTasks();
        return new CommandResult(output);
    }
}
