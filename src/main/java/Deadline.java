public class Deadline extends Task{
    protected String time;
    protected String desc;

    public Deadline(String description){
        super(description);
        String[] arr = description.split("/", 2);
        String[] arr1 = arr[0].split(" ", 2);
        this.time = "by: (" + arr[1] + ")";
        this.desc = arr1[1];
    }

    @Override
    public String toString(){
        return String.format("[D]" + super.isDone() + this.desc + " " + this.time);
    }

    @Override
    public String getDesc(){
        return desc;
    }
}
