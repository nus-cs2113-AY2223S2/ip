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

    public String getSavedData() {
        String taskStatus;
        if (getStatusIcon().equals("X")) {
            taskStatus = "1";
        } else {
            taskStatus = "0";
        }
        String fullDetails = String.join(" / ", "T", taskStatus, taskName, deadline);
        System.out.println(fullDetails);
        return fullDetails;
    }

}
