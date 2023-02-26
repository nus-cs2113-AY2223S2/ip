package inu.commands;

import inu.exceptionhandling.ExceptionManager;
import inu.exceptionhandling.InvalidDate;
import inu.exceptionhandling.InvalidEventFromAndToDate;
import inu.task.Event;
import inu.task.TaskList;

import java.time.LocalDateTime;

/**
 * Adds a new event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final String eventDescription;

    private final LocalDateTime eventFrom;

    private final LocalDateTime eventTo;

    /**
     * Constructor.
     *
     * @param eventDescription description of the event
     * @param eventFrom date and time the event begins.
     * @param eventTo date and time the event ends.
     */
    public EventCommand(String eventDescription, LocalDateTime eventFrom, LocalDateTime eventTo)
            throws InvalidDate, InvalidEventFromAndToDate {
        this.eventDescription = eventDescription;
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
        ExceptionManager.checkCorrectDate(eventFrom, eventTo);
        ExceptionManager.checkValidFromAndToDate(eventFrom, eventTo);
    }

    public CommandResult execute(TaskList taskList) {
        Event eventTask = new Event(eventDescription, eventFrom, eventTo);
        taskList.addTask(eventTask);
        return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");
    }

}
