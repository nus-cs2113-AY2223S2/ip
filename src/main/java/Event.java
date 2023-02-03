public class Event extends Todo {

    protected String from;
    protected String to;
    String deliverable;

    public Event(String inputContents) {
        super(inputContents);
        String[] parts = inputContents.split("/from|/to");

        String deliverable = parts[0].trim();
        from = (parts.length > 1) ? parts[1].trim() : "";
        to = (parts.length > 2) ? parts[2].trim() : "";
        this.deliverable = deliverable;
        this.type = "E";

    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return deliverable + " (from: " + from + "to: " + to + ")";
    }

}
