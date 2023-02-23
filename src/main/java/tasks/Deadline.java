package tasks;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;
    //date stored in yyyy-mm-dd
    LocalDate deadline;

    public Deadline(String ins, String description, String by) {
        super(ins, description);
        this.type = 'D';
        this.by = by;
        setLocalDateDeadline(by);
    }

    public void setLocalDateDeadline(String by){
        String[] arrOfStr = by.split("/", 3);
        int year = Integer.parseInt(arrOfStr[2]);
        int month = Integer.parseInt(arrOfStr[1]);
        int day = Integer.parseInt(arrOfStr[0]);
        deadline = LocalDate.of(year,month,day);
        System.out.println(deadline.getMonth());
    }

    @Override
    public String toString() {
        return ".[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
