package buddy.commands.actionCommands;

import buddy.commands.Command;
import buddy.tasks.*;

public class DeleteTaskCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] deleteSplit = input.split(" ", 2);
            int taskNumberToBeDeleted = Integer.parseInt(deleteSplit[1]);
            int indexOfTaskInTaskList = taskNumberToBeDeleted - 1;
            taskList.deleteTask(indexOfTaskInTaskList);

        } catch(IndexOutOfBoundsException e){
            System.out.println("That is not a valid task to delete! Please check your list again and input a valid task");
        }


    }
}
