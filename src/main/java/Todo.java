public class Todo {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "T";
    }

    public String getStatusIcon() {
        return '[' + (isDone ? "X" : " ") + ']';
    }

    public String getTaskIcon() {
        return '[' + taskType + ']';
    }

}
