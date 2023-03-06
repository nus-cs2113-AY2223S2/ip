package Gilbert.tasks;
public class Deadline extends Task{
    protected String time;
    protected String desc;

    public Deadline(String description){
        super(description);
        String[] arr = description.split("/", 2);
        this.time = "by: (" + arr[1] + ")";
        this.desc = arr[0];
    }

    @Override
    public String toString(){
        return String.format("[D]" + super.getDone() + this.desc + " " + this.time);
    }

    @Override
    public String getDesc(){
        return desc;
    }

    @Override
    public String getType(){
        return "D";
    }
}
