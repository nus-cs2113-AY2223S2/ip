package duke.commands;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    Datetime due;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Deadline(String discription, Datetime due) {
        super(discription);
        this.due = due;
    }

    public String getDue() {
        return due.getDate().format(dateFormatter)+ (!due.hasTime() ? "" : (" "+due.getTime().format(timeFormatter)));
    }

    public String toString() {
        return "[D]"+super.toString() + " ("+due.getDate().format(dateFormatter)+ (!due.hasTime() ? "" : (" "+due.getTime().format(timeFormatter)))+")";
    }
}