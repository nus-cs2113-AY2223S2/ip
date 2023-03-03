package duke.task;

public class Deadline extends Task{
    protected String dueDate;

    /**
     * Creates a deadline item. On top of the description, type and completion status
     * that are set by creating a task item, the deadline is also set based on the inputs.
     * @param description String of the description of be stored in task items.
     * @param type String of the type of the task item.
     * @param by String of the deadline of the deadline item.
     */
    public Deadline(String description, String type, String by) {
        super(description, type);
        this.dueDate = by;
    }

    /**
     * Returns the due date for deadline items on top of printing
     * the description and status of task items.
     * @return String to print.
     */
    @Override
    public String toString() {
        return super.toString() + " by: " + this.dueDate;
    }

}