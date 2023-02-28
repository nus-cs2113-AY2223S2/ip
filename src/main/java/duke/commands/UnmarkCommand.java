package duke.commands;

import duke.data.Task;

public class UnmarkCommand extends Command{
    public static final String COMMAND_WORD = "unmark";
    protected int targetIndex = -1;

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
