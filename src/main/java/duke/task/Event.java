package duke.task;

import duke.output.Symbols;

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
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK.SYMBOL)) {
            taskStatus = Symbols.DATA_MARK.SYMBOL;
        } else {
            taskStatus = Symbols.DATA_UNMARK.SYMBOL;
        }
        String date = String.join(Symbols.EVENT_DATE_DELIMITER.SYMBOL, startDateTime, endDateTime);
        String fullDetails = String.join(Symbols.DATA_DELIMITER.SYMBOL,
                Symbols.EVENT.SYMBOL, taskStatus, taskName, date);
        return fullDetails;
    }
}
