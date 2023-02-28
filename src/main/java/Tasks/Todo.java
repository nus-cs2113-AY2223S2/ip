package Tasks;

public class Todo extends Task {

    /**
     * Constructs todo object.
     * 
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the task for user to read
     * 
     * @return String the description of the task
     */
    @Override
    public String describeTask() {
        return "[T]" + super.describeTask();
    }

    /**
     * Returns the description of the task in a format that is
     * suitable for writing to file.
     * 
     * @return String the description of the task 
     */
    @Override
    public String describeTaskForFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}