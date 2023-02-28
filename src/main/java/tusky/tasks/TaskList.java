package tusky.tasks;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = Objects.requireNonNullElseGet(tasks, ArrayList::new);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index);
        return task;
    }

    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.setDone(false);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
