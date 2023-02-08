public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    public String getStartTime() {
        return description.substring(description.indexOf("/from") + 6, description.indexOf("/to")).trim();
    }

    public String getEndTime() {
        return description.substring(description.indexOf("/to") + 4);
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask().substring(0, super.printTask().indexOf("/from")) +
                "(from: " + getStartTime() + " to: " + getEndTime() + ")\n";
    }
}
