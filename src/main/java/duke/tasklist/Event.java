package duke.tasklist;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toString() {
        return super.toString() + "(from: " + from.replace("from ", "") + " to: " + to + ")";
    }

//    public void getTask(String text) {
//        super.getTask(text);
//        this.description = text.substring(7, text.indexOf("(from: ")-1);
//        this.from = text.substring(text.indexOf("(from: ")+1,text.indexOf("to: ")-1);
//        this.to = text.substring(text.indexOf("to: ")+1, text.indexOf(")")-1);
//    }
}
