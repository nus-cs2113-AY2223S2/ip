package duke.command;

/**
 * Set the status of the task as not completed.
 */
public class MarkCommand extends CommandMarking {
    public MarkCommand(int taskNum) {
        super(taskNum, true);
    }
}
