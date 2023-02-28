package duke.commands;
import duke.outputs.Messages;
import duke.tasks.Task;
import duke.file.TaskList;
import duke.ui.*;

/**
 * Command to unmark a task specified by user in tasklist
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * specifies the index corresponding to the task to be unmarked
     *
     * @param input details of the user command
     */
    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }

    /**
     * unmark the specified task as done in tasklist
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to show message of task being unmarked
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            Task task = tasks.getTask(index - 1);
            task.markAsUndone();
            ui.printTaskStatusMessage(task, "unmark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyUnmarkErrorMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidUnmarkErrorMessage();
        }
    }
}
