public class Event extends Task {
    private String eventDateAndStartTime;
    private String endTime;
    public Event(String description, String eventDateAndStartTime, String endTime) {
        super(description, "E");
        setEventDateAndStartTime(eventDateAndStartTime);
        setEndTime(endTime);
    }

    public String getEventDateAndStartTime() {
        return eventDateAndStartTime;
    }

    public void setEventDateAndStartTime(String eventDateAndStartTime) {
        this.eventDateAndStartTime = eventDateAndStartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
