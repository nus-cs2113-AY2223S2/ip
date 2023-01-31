public class Event extends Task {
    private String fromWhen;
    private String toWhen;

    public Event(String name, String from, String to) {
        super(name, "E");
        this.fromWhen = from;
        this.toWhen = to;
    }

    public String getFromWhen() {
        return this.fromWhen;
    }

    public String getToWhen() {
        return this.toWhen;
    }
}
