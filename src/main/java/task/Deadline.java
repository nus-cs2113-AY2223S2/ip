package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    LocalDate byDate;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }
    public Deadline(String description, boolean isDone, String by){
        super(description,isDone);
        this.by = by;
    }

    public LocalDate getByDate(){
        LocalDate date = LocalDate.parse(by);
        return date;
    }
    @Override
    public String toString(){
        try{
            byDate = getByDate();
            String dateString = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + "(" + "Date: " + dateString + ")";
        }catch (DateTimeParseException e){
            return "[D]" + super.toString() + "(" + by + ")";
        }
    }

    @Override
    public String getLetter(){
        return "D";
    }

    public String getBy(){
        return by;
    }
}
