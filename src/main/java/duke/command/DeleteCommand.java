package duke.command;

import duke.exceptions.UncheckedExceptionHandler;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the delete feature in Duke. Task object specified by the user
 * will be deleted from the task list.
 * e.g., <code>delete 2</code>
 */
public class DeleteCommand {

    /**
     * Deletes task from the saved task list.
     *
     * @param tasks Task list containing all saved task
     * @param taskIndexInput user entered index of task to be deleted
     */
    public static void deleteTask(TaskList tasks, String taskIndexInput){
        try {
            Integer taskIndex = Integer.parseInt(taskIndexInput) - 1;
            Task temp = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            Ui.printBorder();
            System.out.println("Noted. I've removed this task: \n");
            System.out.println(temp + "\n");
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list." );
            Ui.printBorder();
        } catch(IndexOutOfBoundsException | NumberFormatException e){
            UncheckedExceptionHandler.printInvalidTaskIndexMessage();
        }
    }

}
