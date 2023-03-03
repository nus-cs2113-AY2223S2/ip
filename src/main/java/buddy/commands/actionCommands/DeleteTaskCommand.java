package buddy.commands.actionCommands;

import buddy.commands.Command;
import buddy.messages.Messages;
import buddy.tasks.TaskList;


public class DeleteTaskCommand extends Command {

    /**
     * Process DeleteTaskCommand by user and deletes task from task list
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try {
            String[] deleteSplit = input.split(" ", 2);
            int taskNumberToBeDeleted = Integer.parseInt(deleteSplit[1]);
            int indexOfTaskInTaskList = taskNumberToBeDeleted - 1;
            taskList.deleteTask(indexOfTaskInTaskList);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid task to delete! Please check your list again and input a valid task");
            System.out.println(Messages.DIVIDER);
        }
    }
}
