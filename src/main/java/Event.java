public class Event extends Task{
    private String from;
    private String to;
    Event(String name, String from, String to){
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return super.getTaskPrefixWithName() + " (from: " + from + " to: " + to + ")";
    }
}
