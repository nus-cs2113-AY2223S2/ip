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
        String desc = "[E]" + super.getDescription();
        String fromStr = " (FROM: " + eventFrom + ", ";
        String toStr = "TO: " + eventTo + ")";
        desc = desc.concat(fromStr).concat(toStr);
        return desc;
    }
}
