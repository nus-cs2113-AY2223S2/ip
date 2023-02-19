package duke.task;

import java.util.ArrayList;

public class TaskList { // contains the task list e.g., it has operations to add/delete tasks in the list
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskList.get(taskNumber));
    }

    public String getTaskFullDetails(int taskNumber) {
        return taskList.get(taskNumber).getFullTaskDetail();
    }

    public void markTaskDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    public void markTaskNotDone(int taskNumber) {
        taskList.get(taskNumber).markAsNotDone();
    }

    public boolean getIsDone (int taskNumber) {
        return taskList.get(taskNumber).isDone;
    }

    public String getTaskEncoding(int taskNumber) {
        return taskList.get(taskNumber).getSavedData();
    }
}
