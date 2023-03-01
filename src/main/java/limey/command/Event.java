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
    /**
     * Creates a new Event object with the proper name and from and to dates if valid dates are given
     *
     * @param inLine user input without the command term (ie just the event name and its from and to dates)
     */
    public Event(String inLine) throws invalidDateException {
        super(inLine);
        if (!inLine.contains("/from") | !inLine.contains("/to")) {
            throw new invalidDateException();
        }
        int indexOfFrom = inLine.indexOf("/from");
        int indexOfTo = inLine.indexOf("/to");
        inFromDate = inLine.substring(indexOfFrom + 5, indexOfTo).trim();
        inToDate = inLine.substring(indexOfTo + 3).trim();
        try {
            setToDate(inToDate);
            setFromDate(inFromDate);
        } catch (DateTimeParseException e) {
            throw new invalidDateException();
        }
        setTaskName(getTaskName() + " (from " + getFromDate() + " to " + getToDate() + ")");
    }
    /**
     * Returns identity of the current task including the following
     * - task type [E] for event
     * - [X] or [ ] for marked or unmarked tasks - task name
     * - event date and time
     */
    @Override
    public String getTaskIdentity() {
        String eventSymbol = "[E]";
        return eventSymbol + super.getTaskIdentity();
    }
    /**
     * Returns the start date of the event in the format that the user
     * initially input the /from date
     */
    @Override
    public String getInFromDate(){
        return inFromDate;
    }
    /**
     * Returns the end date of the event in the format that the user
     * initially input the /to date
     */
    @Override
    public String getInToDate(){
        return inToDate;
    }
    /**
     * Returns the start date and time of the event in the format that the
     * list should print out the date and time
     */
    public String getFromDate(){
        return fromDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
    /**
     * Returns the end date and time of the event in the format that the
     * list should print out the date and time
     */
    public String getToDate(){
        return toDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
}
