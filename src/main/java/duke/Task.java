package duke;

/**
 * Represents a task that can be added to the list. A <code>Task</code> object can be either a <code>Todo</code>,
 * <code>Deadline</code> or an <code>Event</code>
 */
public class Task {
    protected String description;
    public Task(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public String getBy()
    {
        return null;
    }
    public String getEnd() {
        return null;
    }
}

