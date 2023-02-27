package Tasks;

public class Event extends Task{
    protected String startDateTime, endDateTime;

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
        super(description, "E");
        startDateTime = start;
        endDateTime = end;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(from: " + startDateTime + " to: " + endDateTime + ")";
    }

}
