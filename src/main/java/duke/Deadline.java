package duke;

/**
 * Represents a Deadline task with a specific due date.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the Deadline class.
     * 
     * @param name   the name of the deadline task
     * @param isDone whether the task is marked as done or not
     * @param by     the due date of the task
     */
    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Overrides the toString method to display the task's details as a string.
     * 
     * @return a formatted string representation of the task's details
     */
    public String toString() {
        if (this.getIsDone() == true) {
            return " [D][X] " + this.getName() + " (by: " + this.by + ")";
        }
        return " [D][ ] " + this.getName() + " (by: " + this.by + ")";
    }

    /**
     * Overrides the print method to print the task details in a specific format.
     */
    public void print() {
        if (this.isIsDone() == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }

}
