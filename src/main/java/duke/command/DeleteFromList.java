package duke.command;

import duke.data.TaskData;
import duke.task.Deadline;
import duke.task.Task;

public class DeleteFromList extends Command {
    private int taskIndex;

    public DeleteFromList(String taskIndex) {
        deleteTask(Integer.parseInt(taskIndex) - 1);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        Task taskInfo = taskData.deleteTask(taskIndex);
        System.out.println("Noted sir, I have removed \n"
                + taskInfo.getTaskType() + "[ ]" + taskInfo.getDescription() + "\n"
                + "from the list");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
    }

    public void deleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
