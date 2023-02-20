package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/** Represents an executable command */
public abstract class Command {

    protected TaskList taskList;

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns the result
     */
    public abstract CommandResult execute();

    public static String getFilteredTasksInformation(String output, TaskList taskList) {
        for (int i = 0; i < taskList.getTaskCount(); i += 1) {
            int taskNumber = i + 1;
            String taskInformation = String.join("", " ", Integer.toString(taskNumber), ". ",
                    taskList.getTaskFullDetails(i));
            output = String.join(Ui.NEW_LINE, output, taskInformation);
        }
        return output;
    }
}