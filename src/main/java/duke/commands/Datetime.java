package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public class Datetime {
    protected LocalDate date;
    protected LocalTime time;
    protected Boolean hasTime;

    public Datetime(LocalDate dd) {
        this.date = dd;
        this.hasTime = false;
    }

    public Datetime(LocalDate dd, LocalTime tt) {
        this.date = dd;
        this.time = tt;
        hasTime = true;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Boolean hasTime() {
        return hasTime;
    }
}
