package tusky.tasks;

import tusky.constants.DateTime;
import tusky.exceptions.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from; // datetime as a string
    protected LocalDate to; // datetime as a string
    public Event(String isDone, String description, String from, String to) throws EmptyDescriptionException {
        super(isDone, description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        this.taskType = TaskType.EVENT;
    }

    public Event(String isDone, String description, LocalDate from, LocalDate to) throws EmptyDescriptionException {
        super(isDone, description);
        this.from = from;
        this.to = to;
        this.taskType = TaskType.EVENT;
    }

    public LocalDate getFrom () {
        return from;
    }

    public String getFromString() {
        return from.format(DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
    }

    public void setFrom(String from) {
        this.from = LocalDate.parse(from);
    }
    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo () {
        return to;
    }

    public String getToString() {
        return to.format(DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
    }

    public void setTo(String to) {
        this.to = LocalDate.parse(to);
    }
    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String getTaskSymbol() {
        // E for tusky.tasks.Event
        return "E";
    }

    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, getFromString(), getToString());
    }
}
