package Gilbert.commands;

import Gilbert.messages.Messages;

import Gilbert.tasks.TaskList;

public class CommandMark extends Commands{
    /**
     * Executes the command and marks the specific task as done according to the
     * index provided.
     *
     * @param tasklist   The current list of tasks that have been inputted.
     * @param position   The index of the task that has been done.
     */

    @Override
    public void doCommand(TaskList tasklist, String position){
        int index = Integer.parseInt(position) - 1;
        tasklist.getTask(index).markAsDone();
        System.out.println(Messages.DONE);
        System.out.println(tasklist.getTask(index).toString() + '\n' + Messages.SPACE);
    }
}
