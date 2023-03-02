package AllCommands;

import BasisSupport.Spliterator;
import Support.TaskList;

public class DeleteCommand {
    public static String line;
    public DeleteCommand(String line) {
        DeleteCommand.line = line;
    }

    /**
     * This method is to deal with the deletion request from user.
     * User's input will include the position of task they want to delete.
     * They can use the list command to check which task they want to delete.
     * After doing so, the task list size will decrease by 1
     * Necessary feedback from the system is done by the println functions.
     * It also deals with the case that the input size exceeds the boundary of task list.
     *
     * @param tasks All the tasks we have in the storage
     */

    public static void deleteCommandMethod(TaskList tasks) {
        Spliterator.printSpliterator();
        int deleteIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        // Deal with the case if the delete index mistakes
        if (deleteIndex > tasks.getSize() - 1 || deleteIndex < 0) {
            System.out.println("Oops, you can not delete the task that is outside the boundary.");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasks.getTask(deleteIndex).showTask());
            tasks.deleteTask(deleteIndex);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
        Spliterator.printSpliterator();
    }
}