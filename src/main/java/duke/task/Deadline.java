package duke.task;

public class Deadline extends Task{
    protected String dueDate;

    /**
     * Constructor method for Deadline object.
     * @param description This is the description of the Deadline.
     * @param dueDate This is the dueDate of the Deadline.
     */
    public Deadline(String description, String dueDate){
        super(description, "Deadline");
        this.dueDate = dueDate;
        this.type = "Deadline";
    }

    /**
     * Returns the dueDate of this Deadline.
     * @return String dueDate of the Deadline.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns the details of the Deadline in a specific format.
     * @return String This returns the details of the Deadline.
     */
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.getDueDate() + ")";
    }
}
