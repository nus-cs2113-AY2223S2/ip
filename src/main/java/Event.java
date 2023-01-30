public class Event extends Task{
    protected String from;
    protected String to;

    public Event(int Index, String description, String from, String to) {
        super(Index, description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return super.toString() + "[E]" + "[" + status + "]" + getDescription() + "(from: " + getFrom() + " to: " + getTo();
    }
}
