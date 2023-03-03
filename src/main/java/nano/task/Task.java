package nano.task;

public class Task {
    public static final String TASK_COMPLETED_MARK = "[x] ";
    public static final String TASK_UNCOMPLETED_MARK = "[] ";
    private String name;
    private boolean isCompleted;
    private static int taskCount = 0;
    private static int completedTaskCount = 0;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        taskCount += 1;
    }

    public void setDone() {
        this.isCompleted = true;
        completedTaskCount += 1;
    }

    public void setUndone() {
        this.isCompleted = false;
        completedTaskCount -= 1;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean isCompleted() {
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

    private String todoMark() {
        if (isCompleted()) {
            return TASK_COMPLETED_MARK;
        } else {
            return TASK_UNCOMPLETED_MARK;
        }
    }

    public String toString() {
        return todoMark() + getTaskName();
    }

    public static void deleteTask() {
        taskCount -= 1;
    }
}