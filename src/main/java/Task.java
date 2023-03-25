import java.time.LocalDateTime;

/**
 * Task class containing the basic elements of a task
 * description is a string description of the task itself
 * type is a string of either "[T]", "[D]" or "[E]" to denote the category of task (todo/event/deadline)
 * done is a string of either "[X]" or "[ ]" to denote if the task has been completed
 * DateTime1 and DateTime2 contain the 2 (or less) dates and times tied to each task
 */
public class Task {

    protected String description;
    protected String type;
    protected String done;
    protected LocalDateTime DateTime1;
    protected LocalDateTime DateTime2;

    /**
     * Default Constructor
     */
    public Task(String description, String type, String done, LocalDateTime DateTime1, LocalDateTime DateTime2) {
        this.description = description;
        this.type = type;
        this.done = done;
        this.DateTime1 = DateTime1;
        this.DateTime2 = DateTime2;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getDone() {
        return done;
    }

    public LocalDateTime getDateTime1() {
        return DateTime1;
    }

    public LocalDateTime getDateTime2() {
        return DateTime2;
    }

}
