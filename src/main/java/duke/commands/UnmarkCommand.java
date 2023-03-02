package duke.commands;

import duke.data.Task;

/**
 * the command to unmark the task
 */
public class UnmarkCommand extends Command{
    public static final String COMMAND_WORD = "unmark";
    protected int targetIndex = -1;

    /**
     * Convenience constructor using raw values
     * @param targetIndex the position at which the task to be unmarked
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute() {
        try {
            final Task target = getTargetTask(targetIndex);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(target);
            taskList.getTask(targetIndex).setDone(false);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("OOPS! Your task index exceeds the maximum.");
        }
    }
}
