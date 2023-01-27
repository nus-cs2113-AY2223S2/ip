package tasks;

import java.util.Arrays;

import io.Out;

public class Store {
    private Task[] tasks;

    public Store() {
        this.tasks = new Task[0];
    }

    public void addTask(String taskName) {
        int length = this.tasks.length;
        this.tasks = Arrays.copyOf(this.tasks, length + 1);
        this.tasks[length] = new Task(taskName);
    }

    public void listTasks() {
        for (Task task : tasks) {
            Out.printf("  %s", task.toString());
        }
    }

    public Task getTask(int index) {
        return this.tasks[index];
    }
}
