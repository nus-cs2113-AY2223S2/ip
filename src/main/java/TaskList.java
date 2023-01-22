public class TaskList {
    private String[] tasks;
    private static int tasksCount = 0;

    public TaskList (int expectedTasksCount) {
        this.tasks = new String[expectedTasksCount];
    }


    public String[] getTasks() {
        return this.tasks;
    }

    public void addTask(String task) {
        this.tasks[tasksCount] = task;
        tasksCount++;
    }
}
