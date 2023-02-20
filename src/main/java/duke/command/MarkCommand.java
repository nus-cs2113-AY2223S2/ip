package duke.command;

import duke.data.TaskData;
import duke.task.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String taskIndex) {
        markTask(Integer.parseInt(taskIndex) - 1);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        Task taskInfo = taskData.markAsDone(taskIndex);
        System.out.println("Noted sir, I have marked \n"
                + taskInfo.getTaskType() + "[X] " + taskInfo.getDescription() + "\n"
                + "as done.");
    }

    public void markTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
