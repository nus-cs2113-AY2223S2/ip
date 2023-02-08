public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString(){
        return getTypeIcon() + super.getStatusIcon() + super.description
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
