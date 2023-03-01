package com.ethanyidong.bunny;

import com.ethanyidong.bunny.task.Task;

import java.util.ArrayList;

public class BunnyTaskList {
    private final ArrayList<Task> tasks;

    public BunnyTaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public int numTasks() {
        return this.tasks.size();
    }
}
