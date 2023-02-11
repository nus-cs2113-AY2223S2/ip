package inu.tasklist;

import java.util.ArrayList;
import inu.task.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    private final int INDEX_OFFSET = 1;

    public TaskList() {

        taskList = new ArrayList<>();

    }

    public void printList() {

        for (int taskIndex = 0; taskIndex < taskList.size(); taskIndex++) {

            int taskNumber = taskIndex + INDEX_OFFSET;
            System.out.println(taskNumber + ". " + taskList.get(taskIndex).toString());

        }

    }

    public void addTask(Task t) {

        taskList.add(t);

    }

    public void deleteTask(int taskIndex) {

        taskList.remove(taskIndex);
        taskList.trimToSize();

    }

    public int getTaskListSize() {

        return taskList.size();

    }

    public Task getTask(int index) {

        return taskList.get(index);

    }

    public Task getLastTask() {

        int lastIndex = getTaskListSize() - INDEX_OFFSET;
        return taskList.get(lastIndex);

    }

}