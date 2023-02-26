package limey.command;
import limey.exception.invalidDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private java.time.LocalDateTime fromDate;
    private java.time.LocalDateTime toDate;
    private String inFromDate;
    private String inToDate;
    public void setFromDate(String fromDate) {
        this.fromDate= LocalDateTime.parse(fromDate.trim());
    }

    public void setToDate(String toDate) {
        this.toDate= LocalDateTime.parse(toDate.trim());
    }
    public Event(String inLine) throws invalidDateException{
            super(inLine);
            if(!inLine.contains("/from") |  !inLine.contains("/to")) {
                throw new invalidDateException();
            }
            int indexOfFrom = inLine.indexOf("/from");
            int indexOfTo =  inLine.indexOf("/to");
            inFromDate = inLine.substring(indexOfFrom + 5, indexOfTo).trim();
            inToDate = inLine.substring(indexOfTo + 3).trim();
            try{
                setToDate(inToDate);
                setFromDate(inFromDate);
            } catch (DateTimeParseException e) {
                throw new invalidDateException();
            }
            setTaskName(getTaskName() + " (from " + getFromDate() + " to " + getToDate() + ")");
        }
    @Override
    public String getTaskIdentity() {
        String eventSymbol = "[E]";
        return eventSymbol + super.getTaskIdentity();
    }
    @Override
    public String getInFromDate(){
        return inFromDate;
    }
    @Override
    public String getInToDate(){
        return inToDate;
    }
    public String getFromDate(){
        return fromDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
    public String getToDate(){
        return toDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
}
