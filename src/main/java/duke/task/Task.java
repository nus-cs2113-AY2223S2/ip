package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static DateTimeFormatter printPattern = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
    public static DateTimeFormatter storePattern = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return "task";
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatus() {
        return (isDone() ? "X" : " ");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Boolean isOnDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return description;
    }
}
