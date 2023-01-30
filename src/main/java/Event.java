public class Event extends Tasks {
    private String from;
    private String to;
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    public String formatDate(String from, String to) {
        String f = from.split("\\s+", 2)[1];
        String t = to.substring(to.lastIndexOf("to") + 2);
        return "(" + "from: " + f + "to " + t + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + formatDate(this.from, this.to);
    }
}
