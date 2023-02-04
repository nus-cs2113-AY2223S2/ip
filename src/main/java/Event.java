public class Event extends Task {
    protected String desc;

    public Event(String description){
        super(description);
        String[] arr = description.split(" ", 2);
        this.desc = arr[1];
    }

    @Override
    public String toString(){
        return String.format("[E]" + super.isDone() + this.desc);
    }

    @Override
    public String getDesc(){
        return desc;
    }
}
