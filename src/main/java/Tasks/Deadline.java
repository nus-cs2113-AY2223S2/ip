package Tasks;

import Handlers.Parser;

public class Deadline extends Task {

    protected String dueDate;

    /**
     * Constucts deadline object
     * 
     * @param description Description of the task
     * @param by Due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueDate = by;
    }

    /**
     * Returns the description of the task for user to read
     * 
     * @return String the description of the task
     */
    @Override
    public String describeTask() {
        return "[" + Parser.DEADLINE_TASK_TYPE + "]" + super.describeTask() + " (by: " + dueDate + ")";
    }

    /**
     * Returns the description of the task in a format that is
     * suitable for writing to file.
     * 
     * @return String the description of the task 
     */
    @Override
    public String describeTaskForFile() {
        return Parser.DEADLINE_TASK_TYPE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
