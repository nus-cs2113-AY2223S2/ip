package Task;

public class Deadline extends Task {
    protected String deadlineDay;

    public Deadline(String description, String deadlineDay) {
        super(description);
        this.deadlineDay=deadlineDay;
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" ("+this.deadlineDay+")";
    }

}
