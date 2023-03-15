package duke.commands;

import duke.tasks.Deadline;
import duke.outputs.Messages;
import duke.file.TaskList;
import duke.ui.UI;

/**
 * Command to execute when adding new deadline object to task list
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    /**
     * creates a new deadline object
     *
     * @param input data of given command and description
     */
    public void setDeadline(String input) {
        String[] temp = input.split("deadline | /by "); //separates deadline description and time
        String description = temp[1];
        String by = temp[2];
        this.deadline = new Deadline(description, by);
    }

    /**
     * Adds a new deadline object into the task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print task successfully added statement
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setDeadline(input);
            tasks.addNewTask(deadline);
            ui.printTaskAddedMessage(tasks, deadline);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyDeadlineErrorMessage();
        }
    }
}
