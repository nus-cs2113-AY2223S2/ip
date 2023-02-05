public class Event extends Task {
    private String eventDate;
    private String startTIme;
    private String endTime;
    public Event(String description, String eventDate, String startTime, String endTime) {
        super(description, "E");
        setEventDate(eventDate);
        setStartTIme(startTime);
        setEndTime(endTime);
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStartTIme() {
        return startTIme;
    }

    public void setStartTIme(String startTIme) {
        this.startTIme = startTIme;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
