package Gilbert.commands;


import Gilbert.tasks.TaskList;

import Gilbert.messages.Messages;

public class CommandList extends Commands{
    /**
     * Executes the command and prints the list of Gilbert.tasks currently in the tasklist.
     *
     * @param tasklist      The current list of Gilbert.tasks that have been inputted.
     * @param description   The description of the task.
     */

    @Override
    public void doCommand(TaskList tasklist, String description) {
        if (tasklist.sizeTaskList() != 0) {
            System.out.println(Messages.TASKS);
            for (int i = 0; i < tasklist.sizeTaskList(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + tasklist.getTask(i).toString());
            }
            System.out.println(Messages.SPACE);
        } else {
            System.out.println(Messages.EMPTYLIST);
        }
    }

}
