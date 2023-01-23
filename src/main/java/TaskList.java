import java.util.Arrays;

public class TaskList {
    private Task[] tasks;
    private static int tasksCount = 0;

    public TaskList (int expectedTasksCount) {
        this.tasks = new Task[expectedTasksCount];
    }


    public Task[] getTasks() {
        return Arrays.copyOfRange(this.tasks, 0,  tasksCount);
    }

    public String toString() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        for (Task task: getTasks()) {
            if(task != null) {
                output.append(index++).append(". ").append(task);
            }
        }
        return output.toString();
    }

    public void addTask(String taskName) {
        this.tasks[tasksCount] = new Task(taskName, false);
        tasksCount += 1;
    }
}
