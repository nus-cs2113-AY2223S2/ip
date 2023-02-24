package dataypes;
import exceptions.WrongChrono;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task implements TaskFileHandler {
    protected LocalDate deadline;
    //protected LocalDateTime deadlineTesting;
    public Deadlines(String description, String deadline) throws DateTimeParseException, WrongChrono {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        if(this.deadline.isBefore(LocalDate.now())) {
            throw new WrongChrono();
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
    public Deadlines(){} //more when you get to decode and less in enCode
    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
//    public void printAddedDeadline() {
//        System.out.println("\tGot it. I've added this task:\n");
//        System.out.println("\t\t" + this.getStatusAndDescription());
//    }
    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline);
    }
    @Override
    public String getStatusAndDescription() {
        return "[D]" + super.getStatusAndDescription() + "(by: " + this.getDeadline() + ")";
    }
    public String enCode() {
        return "D # " + super.enCode() + " # " + this.deadline;
    }
}
