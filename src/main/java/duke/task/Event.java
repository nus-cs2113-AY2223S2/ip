package duke.task;

import duke.ui.Symbols;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    // tasks that start at a specific date/time and ends at specific date/time
    public String stringStartDate;
    public String stringEndDate;
    public LocalDate startDate;
    public LocalDate endDate;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;

    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.stringStartDate = startDateTime;
        this.stringEndDate = endDateTime;
        this.startDate = DateTime.storeLocalDate(startDateTime);
        this.startDateTime = DateTime.storeLocalDateTime(startDateTime);
        this.endDate = DateTime.storeLocalDate(endDateTime);
        this.endDateTime = DateTime.storeLocalDateTime(endDateTime);
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        String outStartDate = getOutStartDate();
        String outEndDate = getOutEndDate();
        taskDetail = "[E][" + getStatusIcon() + "] " + this.taskName + " (from: " + outStartDate + " to: "
                + outEndDate + ")";
        return taskDetail;
    }

    private String getOutStartDate() {
        String outStartDate;
        if (this.startDateTime != null) {
            outStartDate = DateTime.outDateTimeFormatter.format(this.startDateTime);
        } else if (this.startDate != null) {
            outStartDate = this.startDate.format(DateTime.outDateFormatter);
        } else {
            outStartDate = this.stringStartDate;
        }
        return outStartDate;
    }

    private String getOutEndDate() {
        String outEndDate;
        if (this.endDateTime != null) {
            outEndDate = DateTime.outDateTimeFormatter.format(this.endDateTime);
        } else if (this.startDate != null) {
            outEndDate = this.endDate.format(DateTime.outDateFormatter);
        } else {
            outEndDate = this.stringEndDate;
        }
        return outEndDate;
    }

    public boolean isDateBetweenEvent(LocalDate date) {
        if (this.startDate == null) {
            return false;
        }
        if (this.endDate == null) {
            return false;
        }
        boolean dateIsAfterStart = this.startDate.isBefore(date);
        boolean dateIsBeforeEnd = this.endDate.isAfter(date);
        return dateIsAfterStart && dateIsBeforeEnd;
    }

    public boolean isDateOnStart(LocalDate date) {
        if (this.startDate == null) {
            return false;
        }
        return this.startDate.equals(date);
    }

    public boolean isDateOnEnd(LocalDate date) {
        if (this.endDate == null) {
            return false;
        }
        return this.endDate.equals(date);
    }

    @Override
    public String getEncodedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        String date = String.join(Symbols.DATA_EVENT_DATE_DELIMITER, stringStartDate, stringEndDate);
        return String.join(Symbols.ENCODE_DATA_DELIMITER, Symbols.EVENT, taskStatus, this.taskName, date);
        // returns full details of task
    }
}
