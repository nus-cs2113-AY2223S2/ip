package buddy.commands.actionCommands;

import buddy.Buddy;
import buddy.messages.Messages;
import buddy.commands.Command;
import buddy.tasks.TaskList;

public class ListCommand extends Command {

    /**
     * Process ListCommand by user and prints out the list of tasks
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        System.out.println(Messages.DIVIDER);
        if (!taskList.isEmpty()) {
            System.out.println("Here is the list of tasks you have remaining! Come on Buddy!");
        }
        int index = 1;                                              // index which shows numbers the task
        for (int i = 0; i < Buddy.taskCount; i++) {
            System.out.println(index + "." + taskList.get(i));
            index++;                                                // increment the number on the task
        }
        if (taskList.isEmpty()) {
            System.out.println("There is nothing in the list! Please enter a new command");
        }
        System.out.println(Messages.DIVIDER);
    }
}
