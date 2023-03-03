package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;

/**
 * Command class for adding tasks of the Deadline type.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    /**
     * Constructs an AddDeadlineCommand object.
     * @param description The description of the Deadline task
     * @param deadline The timing of the deadline
     */
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }


    /**
     * Adds a Deadline task to the task list.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if changes cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = new Deadline(description, deadline);
            tasks.addTask(task);
            ui.printAddTask();
            ui.printTask(task);
            ui.printNoOfTasks(tasks.getSize());
            ui.printSeparator();
            storage.saveData(tasks, ui);
        } catch (IOException e) {
            ui.printSavingError();
            ui.printSeparator();
        }

    }
}
