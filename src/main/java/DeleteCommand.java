/**
 * Command to execute to delete a specified task in task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * creates the index of the task to be deleted
     *
     * @param input data of given command and description
     */
    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }

    /**
     * deletes the task of specified index from task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print task successfully deleted statement
     */
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            ui.printTaskDeletedStatement(tasks, tasks.getTask(index - 1));
            tasks.deleteTask(index - 1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyDeleteMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidDeleteMessage();
        } catch (NumberFormatException exception) {
            Messages.notNumberDeleteMessage();
        }
    }
}
