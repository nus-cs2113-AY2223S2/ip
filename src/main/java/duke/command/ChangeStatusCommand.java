package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the mark and unmark feature in Duke. Task object
 * specified by the user will change its isComplete status.
 * e.g., <code>mark 5</code>
 */
public class ChangeStatusCommand {

    /**
     * Marks task as completed.
     *
     * @param tasks          Task list containing all saved task
     * @param taskIndexInput User entered index of task to be marked completed
     */
    public static void markTask(TaskList tasks, String taskIndexInput){
        try {
            Integer taskIndex = Integer.parseInt(taskIndexInput) - 1;
            tasks.getTask(taskIndex).setStatus("mark");
            System.out.println("Nice! I've marked this task as done: \n");
            System.out.println(tasks.getTask(taskIndex) + "\n");
            Ui.printBorder();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            System.out.println("Error! Specify a valid task index!");
            Ui.printBorder();
        }
    }
    /**
     * Marks task as uncompleted.
     *
     * @param tasks          Task list containing all saved task
     * @param taskIndexInput User entered index of task to be marked uncompleted
     */
    public static void unmarkTask(TaskList tasks, String taskIndexInput){
        try {
            Integer taskIndex = Integer.parseInt(taskIndexInput) - 1;
            tasks.getTask(taskIndex).setStatus("unmark");
            System.out.println("OK, I've marked this task as not done yet: \n");
            System.out.println(tasks.getTask(taskIndex) + "\n");
            Ui.printBorder();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            System.out.println("Error! Specify a valid task index!");
            Ui.printBorder();
        }
    }
}
