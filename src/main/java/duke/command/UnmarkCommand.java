package duke.command;

import duke.data.TaskData;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String taskIndex) {
        unmarkTask(Integer.parseInt(taskIndex) - 1);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        Task taskInfo = taskData.markAsUndone(taskIndex);
        System.out.println("Noted sir, I have marked \n"
                + taskInfo.getTaskType() + "[ ]" + taskInfo.getDescription() + "\n"
                + "as not done.");
    }

    public void unmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
