/**
 * A Events task represents a task with a start time and end time.
 */
public class Events extends Task {
    protected String start;
    protected String end;
    public Events(String description, String startTime, String endTime) {
        super(description);
        start = startTime;
        end = endTime;
    }

    /**
     * This method is used to get the Events icon.
     *
     * @return String This returns Events icon.
     */
    public String getIcon(){
        return StrIntLib.eventIcon;
    }

    /**
     * This method is used to get the start of an Events task.
     *
     * @return String This returns start of task.
     */
    public String getStart() {
        return start;
    }

    /**
     * This method is used to get the end of an Events task.
     *
     * @return String This returns end of task.
     */
    public String getEnd() {
        return end;
    }
}
