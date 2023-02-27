package duke;

public class Task {
    private boolean isDone = false;
    public static int maxTaskNumber = 0;
    private String taskName;
    private String taskType;

    public String getDone() {
        return isDone ? "X" : " ";
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDone() {
        isDone = true;
    }

    public void unsetDone() {
        isDone = false;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskType() {
        return this.taskType;
    }
    /** Gets the stored information of a task depending on its task type **/
    public void getTaskStatus() {
    }
}
