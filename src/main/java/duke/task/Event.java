package duke.task;
import duke.Task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String taskType, String startTime , String endTime){
        super(description,taskType);
        this.startTime = startTime;
        this.endTime = endTime;

    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String saveText(){ return "E | " + (super.isDone ? 1 : 0) + " | "
            + super.description + " | " + startTime + " | " + endTime;
    }
}
