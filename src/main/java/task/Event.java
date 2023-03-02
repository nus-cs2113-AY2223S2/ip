package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDate getByDate(String dateString){
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to){
        super(description,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        try{
            String fromDateString = getByDate(from).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String toDateString = getByDate(to).format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            return "[E]" + super.toString()
                    + "(" + "Date: " + fromDateString + " ~ " + toDateString + ")";
        }catch (DateTimeParseException e){
            return "[E]" + super.toString()
                    + "(" + "" + from + " ~ " + to + ")";
        }

    }

    @Override
    public String getLetter(){
        return "E";
    }

}
