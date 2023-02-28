package duke.commands;

import duke.data.*;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    protected int targetIndex = -1;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute() {
        try {
            final Task target = getTargetTask(targetIndex);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(target);
            taskList.deleteTask(targetIndex);
            taskList.printSize();
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("OOPS! Your task index exceeds the maximum.");
        }

    }
}
