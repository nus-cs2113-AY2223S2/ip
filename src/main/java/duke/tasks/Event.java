package duke.tasks;

public class Event extends Task {
    protected String startTime;
    protected String endTime;


    public Event(String description, String startTime , String endTime){
        super(description,TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }

    @Override
    public String saveText() {
        return super.saveText() + " | " + startTime + " | " + endTime;
    }
}