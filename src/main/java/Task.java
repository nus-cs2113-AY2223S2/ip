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

    public void completeTask() {
        this.isCompleted = true;
        completedTaskCount += 1;
    }

    public void incompleteTask() {
        this.isCompleted = false;
        completedTaskCount -= 1;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean getTaskCompletionStatus() {
        return this.isCompleted;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static int getCompletedTaskCount() {
        return completedTaskCount;
    }

    public static int getUncompletedTaskCount() {
        return taskCount - completedTaskCount;
    }
}
