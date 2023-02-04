package tasks;

import java.util.Arrays;

import exceptions.ArchdukeException;
import io.Out;

public class Store {
    private Task[] tasks;

    public Store() {
        this.tasks = new Task[0];
    }

    public void addTask(Task task) {
        int length = this.tasks.length;
        this.tasks = Arrays.copyOf(this.tasks, length + 1);
        this.tasks[length] = task;
    }

    public void listTasks() throws ArchdukeException {
        for (Task task : tasks) {
            Out.printf("  %s", task.toString());
        }
        Out.printf("You have %d task(s) in the list.", tasks.length);
    }

    public Task getTask(int index) {
        return this.tasks[index];
    }

    public int getTaskCount() {
        return this.tasks.length;
    }
}
