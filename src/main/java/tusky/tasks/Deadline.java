package tusky.tasks;

import tusky.constants.DateTime;
import tusky.exceptions.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    public Deadline(String isDone, String description, String by) throws EmptyDescriptionException {
        super(isDone, description);
        this.by = LocalDate.parse(by);
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline(String isDone, String description, LocalDate by) throws EmptyDescriptionException {
        super(isDone, description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }


    public LocalDate getBy() {
        return by;
    }

    public String getByString() {
        return by.format(DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
    }

    public void setBy(String by) {
        this.by = LocalDate.parse(by);
    }
    public void setBy(LocalDate by) {
        this.by = by;
    }

    @Override
    public String getTaskSymbol() {
        // D for tusky.tasks.Deadline
        return "D";
    }

    @Override
    public String toString(){
        return String.format("%s (by: %s)", description, getByString());
    }
}
