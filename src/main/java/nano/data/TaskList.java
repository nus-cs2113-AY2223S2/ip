package nano.data;

import nano.ui.Ui;
import nano.storage.Storage;
import nano.data.task.Deadline;
import nano.data.task.Event;
import nano.data.task.Task;
import nano.data.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private static final int TASK_NAME_INDEX = 0;
    private static final int TASK_TYPE_INDEX = 1;
    private static final int TASK_START_DATE_INDEX = 2;
    private static final int TASK_DUE_DATE_INDEX = 2;
    private static final int TASK_END_DATE_INDEX = 3;
    private static final int TASK_INDEX_ERROR = -1;
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        storage.getUserData(tasks);

    }

    public void deleteTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            Ui.displayTaskNotFoundMessage();
            return;
        }

        tasks.remove(taskIndex);
        Task.deleteTask();
        System.out.println("Deleted " + taskName);
    }

    public void unmarkTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            Ui.displayTaskNotFoundMessage();
            return;
        }

        if (tasks.get(taskIndex).isCompleted()) {
            tasks.get(taskIndex).setUndone();
            Ui.displayUnmarkTaskMessage(taskName);
        } else {
            Ui.displayTaskAlreadyUndoneMessage(taskName);
        }
    }

    public void markTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            Ui.displayTaskNotFoundMessage();
            return;
        }

        if (!tasks.get(taskIndex).isCompleted()) {
            tasks.get(taskIndex).setDone();
            Ui.displayMarkTaskMessage(taskName);
        } else {
            Ui.displayTaskAlreadyDoneMessage(taskName);
        }
    }

    private int getTaskIndex(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                return tasks.indexOf(task);
            }
        }
        return TASK_INDEX_ERROR;
    }

    public void addTask(String[] taskDetails) {
        if (isInList(taskDetails[TASK_NAME_INDEX])) {
            System.out.println(taskDetails[TASK_NAME_INDEX] + " is already in the list!");
            return;
        }

        Task newTask;
        switch (taskDetails[TASK_TYPE_INDEX]) {
        case "deadline":
            newTask = new Deadline(taskDetails[TASK_NAME_INDEX], taskDetails[TASK_DUE_DATE_INDEX]);
            break;
        case "event":
            newTask = new Event(taskDetails[TASK_NAME_INDEX], taskDetails[TASK_START_DATE_INDEX],
                    taskDetails[TASK_END_DATE_INDEX]);
            break;
        default:
            newTask = new Todo(taskDetails[TASK_NAME_INDEX]);
            break;
        }
        tasks.add(newTask);
        System.out.println("Added " + taskDetails[TASK_NAME_INDEX]);
    }

    public boolean isInList(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}