package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;
import java.io.IOException;

/**
 * Represents the command to delete a task from the task list.
 */
public class DeleteCommand {
    private int taskNumber;
    private static final String REMOVE_MESSAGE = "Noted. I've removed this task:";

    /**
     * Constructs a new DeleteCommand object with the given task number locating the task
     * and deletes this task from the task list.
     * @param taskNumber The position of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        deleteTask();
    }

    private void deleteTask() {
        Task item = Common.tasks.get(taskNumber - 1);
        System.out.println(REMOVE_MESSAGE);
        System.out.println(item.printTask());
        Common.tasks.remove(taskNumber - 1);
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list");
        try {
            Common.dataFile.deleteTask(taskNumber);
        } catch (IOException error) {
            System.out.println(Common.WRITEFILE_EXCEPTION_MESSAGE);
        }
    }

}
