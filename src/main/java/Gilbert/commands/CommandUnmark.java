package Gilbert.commands;

import Gilbert.messages.Messages;
import Gilbert.tasks.TaskList;
public class CommandUnmark extends Commands{
    /**
     * Executes the command and marks the specific task as not done according to the
     * index provided.
     *
     * @param tasklist   The current list of tasks that have been inputted.
     * @param position   The index of the task that has not been done.
     */

    @Override
    public void doCommand(TaskList tasklist, String position){
        int index = Integer.parseInt(position) - 1;
        tasklist.getTask(index).undo();
        System.out.println();
        System.out.println(Messages.UNDONE);
        System.out.println(tasklist.getTask(index).toString() + '\n' + Messages.SPACE);
    }
}
