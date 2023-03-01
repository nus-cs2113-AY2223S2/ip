package duke.task;

public class Deadline extends Task{
    /*
     * Gets the date/time the deadline ends
     *
     * @return date/time string
     */
    public String getEndDate() {
        return endDate;
    }

    /*
     * Sets the date/time the deadline ends
     *
     * @param endDate Date/time string the deadline ends
     */
    public void setEndDate(String endDate) {
        this.endDate = parseDateTimeString(endDate);
    }

    private String endDate;

    /*
     * Initialises the deadline from the user command
     *
     * @param command User command
     */
    public Deadline(String command){
        super();
        setInitCommand(command);
        setDescription(command.substring(command.indexOf(" "), command.indexOf("/by")).trim());
        setEndDate(command.substring(command.indexOf("/by") + 3).trim());
    }

    @Override
    /*
     * Gets a printable formatted string of the deadline
     *
     * @return String of the deadline
     */
    public String toString(){
        return "[D][" + (isDone() ? 'X' : ' ') + "]//////" + super.toString() + " (by: " + getEndDate() + ')';
    }
}
