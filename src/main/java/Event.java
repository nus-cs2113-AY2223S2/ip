public class Event extends Task{
    public String start;
    public String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String checkbox = "[ ]";
        if(status){
            checkbox = "[X]";
        }
        return "[E]" + checkbox + " " + name + "(" + start  + end + ")";
    }
}
