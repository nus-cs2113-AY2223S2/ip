package duke.task;

import duke.ui.Symbols;

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
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        String date = String.join(Symbols.DATA_EVENT_DATE_DELIMITER, startDateTime, endDateTime);
        return String.join(Symbols.DATA_DELIMITER, Symbols.EVENT, taskStatus, taskName, date);
        // returns full details of task
    }
}
