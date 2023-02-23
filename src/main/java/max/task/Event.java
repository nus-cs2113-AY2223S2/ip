package max.task;

public class Event extends Task {
    private String eventFrom;
    private String eventTo;

    public Event(String description, String from, String to) {
        super(description);
        this.eventFrom = from;
        this.eventTo = to;
    }

    @Override
    public String getDescription() {
        String desc = "[" + getTaskLabel() + "]" + super.getDescription();
        String fromStr = " (FROM: " + eventFrom + ", ";
        String toStr = "TO: " + eventTo + ")";
        desc = desc.concat(fromStr).concat(toStr);
        return desc;
    }

    @Override
    public String getTaskLabel() {
        return "E";
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }
}
