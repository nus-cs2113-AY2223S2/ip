package duke.entity;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {

    }

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    public int taskSize() {
        return taskArrayList.size();
    }

    public void removeTask(int index) {
        this.taskArrayList.remove(index);
    }
}
