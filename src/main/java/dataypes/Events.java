package dataypes;
import exceptions.FromAfterTo;
import exceptions.WrongChrono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Events extends Task implements TaskFileHandler {
    protected LocalDate from;
    protected LocalDate to;
    //todo from is after todo, how to sort, custom exception
    public Events(String description, String from, String to) throws DateTimeParseException, FromAfterTo, WrongChrono{
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if(this.from.isAfter(this.to)) {
            throw new FromAfterTo();
        } else if(this.from.isBefore(LocalDate.now()) || this.from.isBefore(LocalDate.now())) {
            throw new WrongChrono();
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
    public Events(){}
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public void setFrom(String from) {
        this.from = LocalDate.parse(from);
    }

    public void setTo(String to) {
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getStatusAndDescription() {
        return "[E]" + super.getStatusAndDescription() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
    public String enCode() {
        return "E # " + super.enCode() + " # " + this.from + " # " + this.to;
    }
}
