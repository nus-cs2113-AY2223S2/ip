package duke.Commands;

import duke.Storage;

import static duke.Duke.LINE_SPACING;

/*
 * Represents a Command to unmar a task
 * inherits from Command
 * */
public class UnMarkCommand extends Command {
    /*
     * The keyword that denotes the command to unmark a task*/
    public static final String COMMAND_WORD = "unmark";
    /*
     * The index to unmark
     * */
    private final int idx;

    /**
     * Creates an instance of UnMarkCommand.
     *
     * @param idx Index of the Task in the TaskList to be unmarked.
     */
    public UnMarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks a Task in the TaskList as not done yet.
     * Prints the Task that has been unmarked and saves the updated TaskList to the storage file.
     */
    public void cmd() {
        tasks.getTask(this.idx).unMark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.getTask(this.idx));
        Storage.saveTasks(tasks);
    }
}
