package duke.commands;

import duke.data.Task;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    protected int targetIndex = -1;

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
