package Tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String getDescription() {
        return "[E]" + getStatusIcon() + super.getDescription() + " (from: " + from + " to:" + to + ")";
    }
    @Override
    public String formatTask() {
        String saveString = "E" + "|" + super.formatTask() + "|" + from + "|" + to;
        return saveString;
    }
}
