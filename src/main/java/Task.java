public class Task {
    private String name;
    private boolean completionStatus;
    private String taskType;

    public Task(String name, String taskType) {
        this.name = name;
        this.taskType = taskType;
        this.completionStatus = false;
    }

    public String getName() {
        return this.name;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setStatus(boolean status) {
        this.completionStatus = status;
    }

    public boolean isDone() {
        return this.completionStatus;
    }

    public String toString() {
        String completionStatusString = completionStatus?"[X]":"[ ]";
        String taskTypeString = '[' + taskType + ']';
        return taskTypeString + completionStatusString + ' ' + name;
    }
}
