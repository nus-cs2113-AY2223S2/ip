package Tasks;

public class Event extends Task {
    protected String from;
    protected String to;
    protected String symbol = "[E]";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description + "(From:" + this.from + "To:" + this.to + ")";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }


    @Override
    public String toString(){
        return "[E]" +  super.getStatusIcon() + " " + super.getDescription();
    }
    @Override
    public String toFile() {
        return this.getStatusIcon() + " : " + "E" + " : " + this.description;
    }
}
