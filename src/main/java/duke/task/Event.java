package duke.task;

public class Event extends Task {
    // tasks that start at a specific date/time and ends at specific date/time
    public String startDateTime;
    public String endDateTime;

    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[E][" + getStatusIcon() + "] " + taskName + " (from: " + startDateTime + " to: "
                + endDateTime + ")";
        return taskDetail;
    }

    public String getSavedData() {
        String taskStatus;
        if (getStatusIcon().equals("X")) {
            taskStatus = "1";
        } else {
            taskStatus = "0";
        }
        String date = String.join(" to ", startDateTime, endDateTime);
        String fullDetails = String.join(" / ", "T", taskStatus, taskName, date);
        System.out.println(fullDetails);
        return fullDetails;
    }
}
