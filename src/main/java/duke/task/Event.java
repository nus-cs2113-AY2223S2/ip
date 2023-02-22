package duke.task;

import duke.ui.Symbols;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Task with a start date and/or time and an end date and/or time */
public class Event extends Task {

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

    /**
     * Generates the task information to be shown to user
     *
     * @return string containing the task information in the format: [E][] taskName (from: date/time to: date/time)
     */
    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        String outStartDate = getOutStartDate();
        String outEndDate = getOutEndDate();
        taskDetail = "[E][" + getStatusIcon() + "] " + this.taskName + " (from: " + outStartDate + " to: "
                + outEndDate + ")";
        return taskDetail;
    }

    /**
     * Generates the most specific start date format to be shown to user given the string start date provided by user
     *
     * @return the most specific date format in string
     */
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

    /**
     * Generates the most specific end date format to be shown to user given the string end date provided by user
     *
     * @return the most specific date format in string
     */
    private String getOutEndDate() {
        String outEndDate;
        if (this.endDateTime != null) {
            outEndDate = DateTime.outDateTimeFormatter.format(this.endDateTime);
        } else if (this.endDate != null) {
            outEndDate = this.endDate.format(DateTime.outDateFormatter);
        } else {
            outEndDate = this.stringEndDate;
        }
        return outEndDate;
    }

    /**
     * Checks whether a given date falls between the task start date and end date
     *
     * @param date date to be checked with
     * @return true if date falls between the start and end date, else otherwise
     */
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

    /**
     * Checks whether a given date falls on the start date
     *
     * @param date date to be checked with
     * @return true if date falls on the start date, false otherwise
     */
    public boolean isDateOnStart(LocalDate date) {
        if (this.startDate == null) {
            return false;
        }
        return this.startDate.equals(date);
    }

    /**
     * Checks whether a given date falls on the end date
     *
     * @param date date to be checked with
     * @return true if date falls on the end date, false otherwise
     */
    public boolean isDateOnEnd(LocalDate date) {
        if (this.endDate == null) {
            return false;
        }
        return this.endDate.equals(date);
    }

    /**
     * Generates the task information to be stored
     *
     * @return string containing the encoded task information in the format: E | 0 | taskName | date to date
     */
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
