package duke.task;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "["+getTaskType()+"]" + super.toString() + " (by: " + by + ")";
    }
    
    @Override
    public String getTaskType() {
    	return "D";
    }
    
    public String getEndTime() {
    	return by;
    }
}
