public class Task {
    private String name;
    private boolean isCompleted;
    private static int taskCount = 0;
    private static int completedTaskCount = 0;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        taskCount += 1;
    }

    // mark task as completed
    public void markTask() {
        this.isCompleted = true;
        completedTaskCount += 1;
    }

    // unmark task to become uncompleted
    public void unmarkTask() {
        this.isCompleted = false;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean getTaskStatus() {
        return this.isCompleted;
    }

    public static int getTaskCount() {
        return taskCount;
    }
    public static int getCompletedTaskCount() {
        return completedTaskCount;
    }
}
