package duke.command;

import duke.task.Event;

public class EventCommand extends AddCommand {
    public String startDate;
    public String endDate;

    public EventCommand(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(new Event(taskName, startDate, endDate));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
