package task;

public class Event extends Task {
    private String from;
    private String to;
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String formatDate(String from, String to) {
        String dateFrom = from.split("\\s+", 2)[1];
        String dateTo = to.substring(to.lastIndexOf("to") + 2);
        return "(" + "from: " + dateFrom + "to" + dateTo + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + formatDate(this.from, this.to);
    }
}
