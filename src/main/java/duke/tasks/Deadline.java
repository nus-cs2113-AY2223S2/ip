package duke.tasks;

/**
 * An extension of the task. Accepts a due data and description.
 */
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