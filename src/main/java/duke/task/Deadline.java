package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime endDate;


    public Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString()
                + " (by: " + ui.getShowDateFormat(endDate) + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public LocalDateTime getEndTime() {
        return endDate;
    }
}
