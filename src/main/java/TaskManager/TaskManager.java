package TaskManager;

import java.util.ArrayList;
import Task.Task;
public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public TaskManager(ArrayList<Task> tasks) {
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
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public Task getTaskByIndex(int indexTask) {
        return tasks.get(indexTask);
    }
    public int getNumTask() {
        return tasks.size();
    }


}