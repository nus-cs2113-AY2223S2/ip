package duke.task;

public class Deadline extends Task {
    // tasks that need to be done before a specific date/time
    public String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[D][" + getStatusIcon() + "] " + taskName + " (by: " + deadline + ")";
        return taskDetail;
    }
}
