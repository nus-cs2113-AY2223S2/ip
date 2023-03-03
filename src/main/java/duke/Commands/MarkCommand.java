package duke.Commands;

import duke.Storage;

import static duke.Duke.LINE_SPACING;

public class MarkCommand extends Command {
    /**
     * The keyword associated with the MarkCommand.
     */
    public static final String COMMAND_WORD = "mark";

    /**
     * The index of the task to be marked.
     */
    private int idx;

    /**
     * Constructs a MarkCommand object with the specified index.
     *
     * @param idx The index of the task to be marked.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done.
     * Saves the updated task list to the storage file.
     */
    public void cmd() {
        tasks.getTask(this.idx).mark();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.getTask(this.idx));
        Storage.saveTasks(tasks);
    }

}
