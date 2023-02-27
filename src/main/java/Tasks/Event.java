package Tasks;

import Tasks.Task;

public class Event extends Task {
    protected String from;
    protected String to;
    protected String symbol = "[E]";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description + "(" + from + to + ")";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString(){
        return "[E]" +  super.getStatusIcon() + super.getDescription() + "(" + from + to + ")";
    }

}
