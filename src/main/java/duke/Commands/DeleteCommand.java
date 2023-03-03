package duke.Commands;

import duke.Storage;
import tasks.Deadline;
import tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int dIdx;
    public DeleteCommand(int dIdx) {
        this.dIdx = dIdx;
    }
    public void cmd() {
        try {
            Task tmp = tasks.getTask(this.dIdx);
            tasks.delete(this.dIdx);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + tmp.getDescription());
            System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
            Storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println("OOPS! Item does not exist, try a different index");
        }
    }
}
