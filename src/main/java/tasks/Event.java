package tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, int num,String from, String to){
        super(description,num);
        this.from = from;
        this.to = to;
    }
    public String toString() {
        return num + ".[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
