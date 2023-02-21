package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;

/**
 * AddDeadlineCommand class that inherits from the AddCommand class
 * Refers to the adding of deadline task type of Command
 */
public class AddDeadlineCommand extends AddCommand {

    /**
     * describes the deadline task that the user is referring to
     */
    protected String description;
    /**
     * when the task should be completed by
     */
    protected String deadline;

    /**
     * Constructor to initialise the new AddDeadlineCommand object with a description and deadline
     *
     * @param description
     * @param deadline
     */
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the Deadline Command by creating a new Deadline Task and saving it to TaskList and Database
     *
     * @param tasks    the TaskList the new deadline task is being added to
     * @param database the Storage the new deadline task is being saved to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Deadline(description, deadline);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
