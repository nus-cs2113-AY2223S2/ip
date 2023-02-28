package duke.commands;

import duke.tasks.Task;
import duke.file.TaskList;
import duke.ui.*;
import duke.outputs.Messages;

/**
 * Command to mark a task specified by user as done in tasklist
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * specifies the index corresponding to the task to be marked as done
     *
     * @param input details of the user command
     */
    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }
    /**
     * mark the specified task as done in tasklist
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to show message of task being marked as done
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            Task task = tasks.getTask(index - 1);
            task.markAsDone();
            ui.printTaskStatusMessage(task, "mark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyMarkErrorMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidMarkErrorMessage();
        }
    }
}
