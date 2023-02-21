package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;

/**
 * AddEventCommand class that inherits from the AddCommand class
 * Refers to the adding of event task type of Command
 */
public class AddEventCommand extends AddCommand {
    /**
     * describes the event task that the user is referring to
     */
    protected String description;
    /**
     * when the event starts
     */
    protected String from;
    /**
     * when the event ends
     */
    protected String to;

    /**
     * Constructor to initialise the new AddDeadlineCommand object with a description and event start and end period
     *
     * @param description describes the event that the user is referring to
     * @param from        is referring to when the event starts
     * @param to          is referring to when the event ends
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the Event Command by creating a new Event Task and saving it to TaskList and Database
     *
     * @param tasks    the TaskList the new event task is being added to
     * @param database the Storage the new event task is being saved to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Event(description, from, to);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
