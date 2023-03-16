package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command and deletes the task according to the index number
 */
public class DeleteCommand extends Command {
    /**
     * Constructor
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Execute the command as below
     * check whether the command is in correct format and
     * Delete the task according to the task number, if the command is valid
     * Or else throw errors
     *
     * @param taskList a TaskList object
     * @param ui       a Ui object
     * @throws CommandFormatException   if the command format is wrong
     * @throws TaskOutOfBoundsException if the task number to be deleted is out of bound
     * @throws TaskException            if the task list is empty
     */
    public void execute(TaskList taskList, Ui ui) throws CommandFormatException,
            TaskOutOfBoundsException, TaskException {
        String[] messages = userInput.split(" ");
        if (messages.length < 2) {
            throw new CommandFormatException();
        } else if (!messages[1].matches("\\d+")) {
            throw new CommandFormatException();
        }

        int taskIndex = Integer.valueOf(messages[1]);

        if (taskIndex <= 0) {
            throw new TaskOutOfBoundsException();
        } else if (taskIndex > taskList.countTaskNumber()) {
            throw new TaskOutOfBoundsException();
        } else if (taskList.countTaskNumber() == 0) {
            throw new TaskException();
        }
        ui.printDeletingLine();
        taskList.findTask(taskIndex).showTaskLine();
        taskList.deleteTask(taskIndex);
    }
}

