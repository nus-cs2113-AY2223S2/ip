package inu.commands;

import inu.task.Event;
import inu.task.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final String eventDescription;

    private final LocalDateTime eventFrom;

    private final LocalDateTime eventTo;

    public EventCommand(String eventDescription, LocalDateTime eventFrom, LocalDateTime eventTo) {
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
