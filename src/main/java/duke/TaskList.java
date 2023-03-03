package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task removeTaskByDisplayedIndex(int displayedIndex) {
        Task task = tasks.get(displayedIndex-1);
        tasks.remove(displayedIndex-1);
        return task;
    }

    public Task markTaskByDisplayedIndex(int displayedIndex) {
        tasks.get(displayedIndex-1).setIsDone(true);
        return tasks.get(displayedIndex-1);
    }

    public Task unmarkTaskByDisplayedIndex(int displayedIndex) {
        tasks.get(displayedIndex-1).setIsDone(false);
        return tasks.get(displayedIndex-1);
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
