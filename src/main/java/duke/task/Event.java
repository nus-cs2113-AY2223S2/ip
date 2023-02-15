package duke.task;
import duke.Task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

<<<<<<< HEAD
    public Event(String description, String taskType, String startTime , String endTime){
        super(description,taskType);
=======
    public Event(String description, String taskType, String startTime, String endTime) {
        super(description, taskType);
>>>>>>> branch-Level-7
        this.startTime = startTime;
        this.endTime = endTime;
    }
<<<<<<< HEAD
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String saveText(){ return "E | " + (super.isDone ? 1 : 0) + " | "
            + super.description + " | " + startTime + " | " + endTime;
=======

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " +
                "(from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String saveText() {
        return "E | " + (super.isDone ? 1 : 0) + " | " +
                super.description + " | " + startTime + " | " + endTime;
>>>>>>> branch-Level-7
    }
}