package dev.joulev.archduke.tasks;

import java.util.ArrayList;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.io.Out;

public class Store {
    private ArrayList<Task> tasks;

    public Store() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public void listTasks() throws ArchdukeException {
        for (Task task : tasks) {
            Out.printf("  %s", task.toString());
        }
        Out.printf("You have %d task(s) in the list.", getTaskCount());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }
}
