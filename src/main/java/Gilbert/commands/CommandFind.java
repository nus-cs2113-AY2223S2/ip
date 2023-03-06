package Gilbert.commands;

import Gilbert.tasks.TaskList;

import Gilbert.messages.Messages;

public class CommandFind extends Commands{
    /**
     * Executes the command and finds all Gilbert.tasks that contains the keyword.
     * Prints out all the Gilbert.tasks found.
     *
     * @param taskList  The current list of Gilbert.tasks that have been inputted.
     * @param keyword   The keyword to find the Gilbert.tasks in the tasklist.
     */

    @Override
    public void doCommand(TaskList taskList, String keyword) {
        String list = "";
        int index = 1;
        for (int i = 0; i < taskList.sizeTaskList(); i++){
            if (taskList.getTask(i).getDesc().toLowerCase().contains(keyword.toLowerCase())) {
                list += index + ". " + taskList.getTask(i).toString() + System.lineSeparator();
                index++;
            }
        }
        if (list.equals("")) {
            System.out.println(Messages.NOTFOUND);
        } else {
            System.out.println(Messages.FIND + '\n' + list + Messages.SPACE);
        }
    }
}
