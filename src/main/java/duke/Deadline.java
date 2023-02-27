package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public void getTaskStatus() {
        System.out.printf("[D][%s] %s (by: %s)\n", this.getDone(), this.getTaskName(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    public LocalDate getBy() {
        return this.by;
    }
}
