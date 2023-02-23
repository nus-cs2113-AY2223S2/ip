package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    public static final String dateTimeParseFormat = "yyyy/MM/dd' 'HH:mm";
    public static final String dateTimePrintFormat = "MMM dd yyyy hh:mm a";
    public static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern(dateTimeParseFormat);
    public static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern(dateTimePrintFormat);
    
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public char getTypeIcon() {
        return type;
    }

    public void mark(boolean done) {
        isDone = done;
    }

    public abstract String getTimeBound();
    public abstract boolean haveValidDate();
    public abstract LocalDateTime getEndTime();

    @Override
    public String toString() {
        return ("[" + getTypeIcon() + "] " + "[" + getStatusIcon() + "] " + getDescription());
    }
}