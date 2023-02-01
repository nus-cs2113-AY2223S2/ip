public class Event extends Task {
    String start; String end;
    public Event (String description, String start, String end) {
        super(description + "(from: " + start + "to: " + end + ")");
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }
}
