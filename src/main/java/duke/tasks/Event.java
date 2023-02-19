package duke.tasks;

public class Event extends Task {
    
    // Attributes
    public String startTime;
    public String endTime;

    // Constructor
    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }
    
    // Overriden printTask method
    public String printTask() {
        if (this.isComplete) {
            return "[E][X] " + this.description;
        }
        return "[E][ ] " + this.description; 
    }
}