import duke.task.Task;

/**
 * Command to execute to unmark a specified task as done in task list
 */
public class UnmarkCommand extends Command {
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
     * unmark the specified task as done in task list
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
            task.setAsUndone();
            ui.printTaskStatusStatement(task, "unmark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyUnmarkMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidUnmarkMessage();
        } catch (NumberFormatException exception) {
            Messages.notNumberUnmarkMessage();
        }
    }
}