package duke.tasks;

public class Event extends Task {
    public String start;
    public String end;
    public Event(String name, String start, String end, Boolean status) {
        super(name, status);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String checkbox = "[ ]";
        if(status){
            checkbox = "[X]";
        }
        return "[E]" + checkbox + " " + name + "(from: " + start  + ", to: " + end + ")";
    }

    @Override
    public String toTextFileFormat(){
        return "event/" + name + "/" + status + "/" + start + "/" + end;
    }
}
