package dev.joulev.archduke.tasks;

import java.util.ArrayList;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.io.Out;
import dev.joulev.archduke.storage.Storage;

public class Store {
    private ArrayList<Task> tasks;

    public Store() {
        this.tasks = Storage.readSavedTasks();
    }

    public void onMutation() throws ArchdukeException {
        Storage.saveTasks(tasks);
    }

    public void addTask(Task task) throws ArchdukeException {
        this.tasks.add(task);
        onMutation();
    }

    public void toggleTaskCompleteness(int index) throws ArchdukeException {
        Task task = getTask(index);
        task.toggleCompleted();
        onMutation();
    }

    public void listTasks() throws ArchdukeException {
        for (Task task : tasks) {
            Out.printf("  %s", task.toString());
        }
        Out.printf("You have %d task(s) in the list.", getTaskCount());
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }
}
