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

    public int getTasksCount() {
        return tasksCount;
    }

    public String toString() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        for (Task task: tasks) {
            if(task != null) {
                output.append(index).append(". ").append(task);

                if(index != tasksCount){
                    output.append(System.lineSeparator());
                }
                index++;
            }
        }
        return output.toString();
    }

    public void addTask(Task task) {
        this.tasks[tasksCount] = task;
        tasksCount += 1;
    }
}
