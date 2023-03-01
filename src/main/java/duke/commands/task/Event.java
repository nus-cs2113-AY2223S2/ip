package duke.commands.task;

import duke.commands.CommandResult;

public class Event extends Task {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_ADD_EVENT_SUCCESS = "Added event: %1$s";
    public Event addEvent;

    public String from;
    public String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
        taskChar = "[E]";
        setFormattedTask();
    }

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription
                + " (from: " + from + " to: " + to + ")";
    }

    public CommandResult execute() {
        addEvent = this;
        taskList.addTask(addEvent);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, formattedTask));
    }
}
