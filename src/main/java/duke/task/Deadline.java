package duke.task;

import duke.ui.Symbols;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    // tasks that need to be done before a specific date/time
    public String deadline;
    public LocalDate date;
    public LocalDateTime dateTime;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
        this.date = DateTime.storeLocalDate(deadline);
        this.dateTime = DateTime.storeLocalDateTime(deadline);
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        String outputDeadline = getOutDeadline();
        taskDetail = "[D][" + getStatusIcon() + "] " + this.taskName + " (by: " + outputDeadline + ")";
        return taskDetail;
    }

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

    public boolean isDateOnDeadline(LocalDate date) {
        if (this.date == null) {
            return false;
        }
        return this.date.equals(date);
    }

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
