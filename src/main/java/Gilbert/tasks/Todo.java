package Gilbert.tasks;
public class Todo extends Task {
    protected String desc;

    public Todo(String description){
        super(description);
        this.desc = description;
    }

    /**
     * Overrides standard string printing format.
     *
     */
    @Override
    public String toString(){
        return String.format("[T]" + super.getDone() + this.desc);
    }

    @Override
    public String getDesc(){
        return desc;
    }

    @Override
    public String getType(){
        return "T";
    }
}
