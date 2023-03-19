package Gilbert.tasks;
public class Event extends Task {
    protected String desc;

    public Event(String description){
        super(description);
        this.desc = description;
    }

    @Override
    public String toString(){
        return String.format("[E]" + super.getDone() + this.desc);
    }

    @Override
    public String getDesc(){
        return desc;
    }

    @Override
    public String getType(){
        return "E";
    }
}
