package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * It formats the code depending on the time input by the user
     * @return it formats the code so that it displays like for example Oct 24 2019
     */

    @Override
    public String toString() {
        //return "[D]" + super.toString() + " ( by: " + by + ")";
        //by = by.substring(0, 8) + "0" + by.substring(8);
        LocalDate d1 = LocalDate.parse(by);
        return "[D]" + super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

}

