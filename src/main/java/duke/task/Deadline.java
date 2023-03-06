package duke.task;

import duke.ui.Symbols;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task with a deadline
 */
public class Deadline extends Task {

    public String deadline;
    public LocalDate date;
    public LocalDateTime dateTime;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
        this.date = DateTime.storeLocalDate(deadline);
        this.dateTime = DateTime.storeLocalDateTime(deadline);
    }

    /**
     * Generates the task information to be shown to user
     *
     * @return string containing the task information in the format: [T][] taskName (by: date)
     */
    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        String outputDeadline = getOutDeadline();
        taskDetail = "[D][" + getStatusIcon() + "] " + this.taskName + " (by: " + outputDeadline + ")";
        return taskDetail;
    }

    /**
     * Generates the most specific date format to be shown to user given the string date provided by user
     *
     * @return the most specific date format in string
     */
    private String getOutDeadline() {
        String outputDeadline;
        if (this.dateTime != null) {
            outputDeadline = DateTime.outDateTimeFormatter.format(this.dateTime);
        } else if (this.date != null) {
            outputDeadline = this.date.format(DateTime.outDateFormatter);
        } else {
            outputDeadline = this.deadline;
        }
        return outputDeadline;
    }

    /**
     * Checks whether a given date falls on the deadline
     *
     * @param date date to be checked with
     * @return true if date falls on the deadline, false otherwise
     */
    public boolean isDateOnDeadline(LocalDate date) {
        if (this.date == null) {
            return false;
        }
        return this.date.equals(date);
    }

    /**
     * Generates the task information to be stored
     *
     * @return string containing the encoded task information in the format: D | 0 | taskName | date
     */
    @Override
    public String getEncodedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        return String.join(Symbols.ENCODE_DATA_DELIMITER, Symbols.DEADLINE, taskStatus, this.taskName, this.deadline);
    }
}
