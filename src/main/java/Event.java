public class Event extends Task{
    private String from;
    private String to;
    public Event(String name, Boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + "from: " + from + " to: " + to + ")";
    }
}
