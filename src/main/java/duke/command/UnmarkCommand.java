package duke.command;

/**
 * Set the status of the task as not completed.
 */
public class UnmarkCommand extends CommandMarking {
    public UnmarkCommand(int taskNum) {
        super(taskNum, false);
    }
}
