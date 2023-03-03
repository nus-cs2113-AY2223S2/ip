package buddy.commands.actionCommands;

import buddy.commands.Command;
import buddy.messages.Messages;
import buddy.tasks.TaskList;
import buddy.tasks.Task;

public class UnmarkTaskCommand extends Command {

    /**
     * Processes UnmarkTaskCommand by marking the task as not done
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try {
            String[] unmarkSplit = input.split(" ", 2);
            int taskNumberToBeUnmarked = Integer.parseInt(unmarkSplit[1]);
            int indexOfTaskToBeUnmarked = taskNumberToBeUnmarked - 1;
            Task taskToBeUnmarked = taskList.get(indexOfTaskToBeUnmarked);
            taskToBeUnmarked.markAsUndone();

        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.DIVIDER);
            System.out.println("That is not a valid task to unmark! Please check your list again and input a valid task");
            System.out.println(Messages.DIVIDER);
        }

    }
}