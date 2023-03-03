package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Deadline;

public class DeadLineCommand extends Command {
    /**
     * The Deadline object to be added to the list of tasks.
     */
    private Deadline deadline;

    /**
     * The command word for a DeadlineCommand object.
     */
    public static final String COMMAND_WORD = "deadline";

    /**
     * The description of the Deadline task.
     */
    private String desc;

    /**
     * A boolean flag indicating whether the Deadline task is marked as done.
     */
    private boolean isMark;

    /**
     * The deadline of the Deadline task.
     */
    private String by;

    /**
     * Constructs a DeadlineCommand object with the specified
     * description, mark status, and deadline.
     *
     * @param desc   The description of the Deadline task.
     * @param isMark A boolean flag indicating whether the Deadline task is marked as done.
     * @param by     The deadline of the Deadline task.
     */
    public DeadLineCommand(String desc, boolean isMark, String by) {
        this.desc = desc;
        this.isMark = isMark;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand by adding a new Deadline
     * task to the list of tasks, printing a message to confirm
     * the addition, and saving the tasks to the storage file.
     *
     * @throws DukeException If an error occurs while executing the command.
     */
    public void cmd() throws DukeException {
        Deadline dL = new Deadline(this.desc, this.isMark, this.by);
        tasks.add(dL);
        addTaskPrint(tasks, dL);
        Storage.saveTasks(tasks);
    }
}
