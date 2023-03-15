package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.exceptions.TaskUndoneException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an unmark command
 * Change the task to undone according to the index number
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor
     */
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Execute the command as below
     * Check the command format and unmark the task according to the task number
     *
     * @param taskList a TaskList object
     * @param ui       a Ui object
     * @throws TaskOutOfBoundsException if the task number is out of bound
     * @throws TaskUndoneException      if the user wants to unmark the undone task
     * @throws TaskException            if the task list is empty
     * @throws CommandFormatException   if the command is in incorrect format
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskOutOfBoundsException,
            TaskUndoneException, TaskException, CommandFormatException {
        String[] messages = userInput.trim().replace("unmark ", "").split(" ");
        if (messages.length < 1) {
            throw new CommandFormatException();
        } else if (!messages[0].matches("\\d+")) {
            throw new CommandFormatException();
        } else if (Integer.valueOf(messages[0]) > taskList.countTaskNumber()) {
            throw new TaskOutOfBoundsException();
        } else if (Integer.valueOf(messages[0]) <= 0) {
            throw new TaskOutOfBoundsException();
        } else if (taskList.countTaskNumber() == 0) {
            throw new TaskException();
        }
        int taskIndex = Integer.valueOf(messages[0]);

        if (!taskList.findTask(taskIndex).isCompleted()) {
            throw new TaskUndoneException();
        }
        ui.printTaskUndoneLine();
        taskList.markAsUndone(taskIndex);
        System.out.println(taskList.showTaskMessage(taskIndex - 1));
    }
}

