package Gilbert.commands;

import Gilbert.messages.Messages;
import Gilbert.tasks.Task;
import Gilbert.tasks.TaskList;

import javax.print.attribute.standard.NumberOfDocuments;

public class CommandDelete extends Commands{
    /**
     * Executes the command and deletes the specific entry of the tasklist.
     *
     * @param tasklist   The current list of tasks that have been inputted.
     * @param position   The index of the task to be deleted.
     */

    @Override
    public void doCommand(TaskList tasklist, String position){
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = tasklist.getTask(index);
            tasklist.deleteTask(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.getDesc());
            System.out.println("Now you have " + tasklist.sizeTaskList() + " tasks in your list.\n" + Messages.SPACE);
        } catch (NumberFormatException e) {
            System.out.println(Messages.INDEX);
        }
    }
}
