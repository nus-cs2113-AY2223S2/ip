package duke.tasks;

public class Deadline extends Task {
    
    // New attribute
    public String dueDate;

    // Constructor
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    
    // Overriden printTask method
    public String printTask() {
        if (this.isComplete) {
            return "[D][X] " + this.description;
        }
        return "[D][ ] " + this.description; 
    }
}