package duke.commands;

import duke.data.Task;

/**
 * The command to mark a task to be done
 */
public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    protected int targetIndex = -1;

    /**
     * Convenience constructor using raw values
     * @param targetIndex the position at which the task to be marked done
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute() {
        try {
            final Task target = getTargetTask(targetIndex);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target);
            taskList.getTask(targetIndex).setDone(true);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("OOPS! Your task index exceeds the maximum.");
        }
    }
}
