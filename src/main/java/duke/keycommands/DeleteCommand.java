package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;
import java.io.IOException;

public class DeleteCommand {
    private int taskNumber;
    private static final String REMOVE_MESSAGE = "Noted. I've removed this task:";
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
