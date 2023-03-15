package duke.commands;

import duke.outputs.Messages;
import duke.file.TaskList;
import duke.ui.UI;

/**
 * Command to execute to delete a specified task in task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * specifies the index corresponding to the task to be deleted
     *
     * @param input details of the user command
     */
    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }

    /**
     * deletes the task of specified index from task list
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to show task deletion message
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            ui.printTaskDeletedMessage(tasks, tasks.getTask(index - 1));
            tasks.deleteSpecifiedTask(index - 1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyDeleteErrorMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidDeleteErrorMessage();
        } catch (NumberFormatException exception) {
            Messages.nonNumberInputErrorMessage();
        }
    }
}
