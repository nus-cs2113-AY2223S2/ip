package duke.Commands;

import duke.Storage;
import tasks.Deadline;
import tasks.Task;

/**
 * Subclass of Command for delete
 */
public class DeleteCommand extends Command {
    /**
     * The keyword associated with the DeleteCommand.
     */
    public static final String COMMAND_WORD = "delete";

    /**
     * The index of the task to be deleted.
     */
    private int dIdx;

    /**
     * Constructs a DeleteCommand object with the specified
     * index of the task to be deleted.
     *
     * @param dIdx The index of the task to be deleted.
     */
    public DeleteCommand(int dIdx) {
        this.dIdx = dIdx;
    }

    /**
     * Executes the DeleteCommand by removing the specified task
     * from the task list.
     * Also prints out a message indicating the task that
     * was removed and the current number of tasks in the list.
     *
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public void cmd() {
        try {
            Task tmp = tasks.getTask(this.dIdx);
            tasks.delete(this.dIdx);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + tmp.getDescription());
            System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
            Storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println("OOPS! Item does not exist, try a different index");
        }
    }
}
