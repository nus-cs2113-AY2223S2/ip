package duke.tasks;

/**
 * This class inherits from Task class.
 * Events contain a description, a start date and an end date.
 */
public class Event extends Task {
    private String eventStartInfo;
    private String eventEndInfo;

    /**
     * Creates Event object
     * @param description Event description
     * @param eventStartInfo Event start information
     * @param eventEndInfo Event end information
     */
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
