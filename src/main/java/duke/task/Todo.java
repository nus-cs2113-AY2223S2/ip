package duke.task;

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

    public String getSavedData() {
        String taskStatus;
        if (getStatusIcon().equals("X")) {
            taskStatus = "1";
        } else {
            taskStatus = "0";
        }
        String fullDetails = String.join(" / ", "T", taskStatus, taskName);
        System.out.println(fullDetails);
        return fullDetails;
    }
}
