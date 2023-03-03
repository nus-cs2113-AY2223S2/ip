package duke.Commands;

import duke.TaskList;
import dukeException.DukeException;
import tasks.Task;

import static duke.Duke.LINE_SPACING;

/**
 * The Command class is an abstract class that serves
 * as the base class for all types of commands in Duke.
 */
public class Command {

    /**
     * The list of tasks in Duke.
     */
    protected TaskList tasks = new TaskList();

    /**
     * A boolean flag that indicates whether the program should exit.
     */
    protected boolean isExit = false;

    /**
     * Prints a message indicating that a task has been added to the task list.
     *
     * @param tasks The list of tasks in Duke.
     * @param tsk   The task that was added to the list.
     */
    public static void addTaskPrint(TaskList tasks, Task tsk) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tsk.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Retrieves the task at the specified index in the list of tasks.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int idx) {
        return tasks.getTask(idx);
    }

    /**
     * Executes the command.
     *
     * @throws DukeException If an error occurs while executing the command.
     */
    public void cmd() throws DukeException {
        System.out.println("Please enter in a valid command.");
    }

    /**
     * Returns a boolean flag indicating whether the program should exit.
     *
     * @return A boolean flag indicating whether the program should exit.
     */
    public boolean getExit() {
        return isExit;
    }
}
