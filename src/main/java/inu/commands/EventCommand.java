package inu.commands;

import inu.task.Event;
import inu.task.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final String eventDescription;

    private final String eventFrom;

    private final String eventTo;

    public EventCommand(String eventDescription, String eventFrom, String eventTo) {
        this.eventDescription = eventDescription;
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    public CommandResult execute(TaskList taskList) {
        Event eventTask = new Event(eventDescription, eventFrom, eventTo);
        taskList.addTask(eventTask);
        return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");
    }

}
