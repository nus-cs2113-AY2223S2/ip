package duke.command;

import duke.task.Task;
import duke.data.TaskList;
import duke.ui.*;

/**
 * Command to execute to mark a specified task as done in task list
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * creates the index of the task to be marked as done
     *
     * @param input data of given command and description
     */
    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }
    /**
     * mark the specified task as done in task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print all tasks in task list
     */
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            Task task = tasks.getTask(index - 1);
            task.setAsDone();
            ui.printTaskStatusStatement(task, "mark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyMarkMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidMarkMessage();
        } catch (NumberFormatException exception) {
            Messages.notNumberMarkMessage();
        }
    }
}
