package duke.task;

public class Event extends Task{
    private String startDate;
    private String endDate;

    /*
     * Returns the start date/time of the event
     *
     * @return Start date/time string of the event
     */
    public String getStartDate() {
        return startDate;
    }

    /*
     * Sets the start date/time of the event
     *
     * @param startDate String start date/time of the event
     */
    public void setStartDate(String startDate) {
        this.startDate = parseDateTimeString(startDate);
    }

    /*
     * Gets the end date/time of the event
     *
     * @return End date/time string of the event
     */
    public String getEndDate() {
        return endDate;
    }

    /*
     * Sets the end date/time of the event
     *
     * @param startDate String end date/time of the event
     */
    public void setEndDate(String endDate) {
        this.endDate = parseDateTimeString(endDate);
    }

    /*
     * Initialises the event from the user command
     *
     * @param command User command
     */
    public Event(String command){
        super();
        setInitCommand(command);
        setDescription(command.substring(command.indexOf(" "), command.indexOf("/from")).trim());
        setStartDate(command.substring(command.indexOf("/from") + 5, command.indexOf("/to")).trim());
        setEndDate(command.substring(command.indexOf("/to") + 3).trim());
    }
    @Override
    /*
     * Gets a printable formatted string of the event
     *
     * @return String of the event
     */
    public String toString(){
        return "[E][" + (isDone() ? 'X' : ' ') + "]//////" + super.toString() + " (from: " + getStartDate()
                + " to: " + getEndDate() + ')';
    }
}
