package task;

public class Event extends Task {
    String from;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    String to;
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to){
        super(description,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString()
                + "(" + "from " + from + " to " + to + ")";
    }

    @Override
    public String getLetter(){
        return "E";
    }

}
