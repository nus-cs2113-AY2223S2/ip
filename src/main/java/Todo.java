public class Todo extends Task {
    protected String desc;

    public Todo(String description){
        super(description);
        String[] arr = description.split(" ", 2);
        this.desc = arr[1];
    }

    @Override
    public String toString(){
        return String.format("[T]" + super.isDone() + this.desc);
    }

    @Override
    public String getDesc(){
        return desc;
    }
}
