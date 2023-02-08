public class Todo extends Task {
    // tasks without any date/time attached to it

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[T][" + getStatusIcon() + "] " + taskName;
        return taskDetail;
    }
}
