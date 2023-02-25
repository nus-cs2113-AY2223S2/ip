package task;
import exception.InvalidIndexException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
    public Task getTask(int taskIdx) throws InvalidIndexException {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            throw new InvalidIndexException("Please ensure you enter the correct task number");
        }
        return tasks.get(taskIdx);
    }

    public int getTasksCount() {
        return tasks.size();
    }
    public void addTask (Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    public Task removeTask(int taskIdx) throws InvalidIndexException {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            throw new InvalidIndexException("Please ensure you enter the correct task number");
        }
        return tasks.remove(taskIdx);
    }

}
