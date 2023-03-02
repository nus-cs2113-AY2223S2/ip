package duke.tasks;

public class Event extends Task {
    private String eventStartInfo;
    private String eventEndInfo;

    public Event(String description, String eventStartInfo, String eventEndInfo) {
        super(description, "E");
        setEventStartInfo(eventStartInfo);
        setEventEndInfo(eventEndInfo);
    }

    public String getEventStartInfo() {
        return eventStartInfo;
    }

    public void setEventStartInfo(String eventStartInfo) {
        this.eventStartInfo = eventStartInfo;
    }

    public String getEventEndInfo() {
        return eventEndInfo;
    }

    public void setEventEndInfo(String eventEndInfo) {
        this.eventEndInfo = eventEndInfo;
    }
}
