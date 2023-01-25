import java.util.Arrays;

public class TaskStore {
    private String[] tasks;

    public TaskStore() {
        this.tasks = new String[0];
    }

    public void addTask(String taskName) {
        int length = this.tasks.length;
        this.tasks = Arrays.copyOf(this.tasks, length + 1);
        this.tasks[length] = taskName;
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.length; i++) {
            IO.printf("  %d: %s", i + 1, this.tasks[i]);
        }
    }
}
