package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int indexTask) {
        Task deletedTask = tasks.get(indexTask);
        tasks.remove(indexTask);
        return deletedTask;
    }

    public int getNumTask() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTaskByIndex(int indexTask) {
        return tasks.get(indexTask);
    }


}
