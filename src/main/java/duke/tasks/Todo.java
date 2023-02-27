package duke.tasks;

/**
 * An extension of the task class, and only contains a description.
 */
public class Todo extends Task {

    // Call task constructor after the word todo
    public Todo(String description) {
        super(description.substring(description.indexOf(" ") + 1));
    }

    // Overriden printTask method
    public String printTask() {
        if (this.isComplete) {
            return "[T][X] " + this.description;
        }
        return "[T][ ] " + this.description; 
    }
}