import java.util.Arrays;

public class TaskStore {
    private Task[] tasks;

    public TaskStore() {
        this.tasks = new Task[0];
    }

    public void addTask(String taskName) {
        int length = this.tasks.length;
        this.tasks = Arrays.copyOf(this.tasks, length + 1);
        this.tasks[length] = new Task(taskName);
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.length; i++) {
            this.tasks[i].printTask(i);
        }
    }

    public Task getTask(int index) {
        return this.tasks[index];
    }
}
