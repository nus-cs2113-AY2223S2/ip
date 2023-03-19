package Gilbert.commands;

import Gilbert.tasks.TaskList;

import Gilbert.messages.Messages;
public class CommandHelp extends Commands{
    /**
     * Executes the command and prints out the help message.
     *
     * @param taskList      The current list of tasks that have been inputted.
     * @param description   The description of the task.
     */
    @Override
    public void doCommand(TaskList taskList, String description) {
        System.out.println(Messages.HELP);
    }
}
