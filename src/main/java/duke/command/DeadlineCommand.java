package duke.command;

import duke.task.Deadline;

public class DeadlineCommand extends AddCommand {
    public String deadline;
    public DeadlineCommand(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Deadline(taskName, deadline));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
