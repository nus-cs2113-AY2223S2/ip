package duke.commands;

import java.time.format.DateTimeFormatter;

public class Event extends Task {
    Datetime due;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String discription, Datetime due) {
        super(discription);
        this.due = due;
    }

    public String getDue() {
        return due.getDate().format(dateFormatter)
                + (!due.hasTime() ? "" : (" " + due.getTime().format(timeFormatter)));
    }

    public String toString() {
        return "[E]" + super.toString() + " (" + due.getDate().format(dateFormatter)
                + (!due.hasTime() ? "" : (" " + due.getTime().format(timeFormatter))) + ")";
    }
}
