package Tasks;

public class Event extends Task{
    protected String type = "E";
    protected String startDateTime, endDateTime;

    public String getType() {
        return type;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Event(String description, String start, String end) {
        super(description);
        startDateTime = start;
        endDateTime = end;
    }
    @Override
    public String toString() {
        return '[' + type + "]" + super.toString() + "(from:" + startDateTime + " to:" + endDateTime + ")";
    }

}
