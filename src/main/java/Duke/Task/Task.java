package Duke.Task;

/**
 * Represents a Task object which is identified by the type of task it is (taskLabel), its content (description)
 * and whether it is completed or not (mark).
 */
public class Task {
    protected String taskLabel;
    protected String description;
    protected String mark;

    public Task(String input) {
        this.description = input;
        this.taskLabel = "To be replaced by labels";
        this.mark = "[ ]";
    }

    public String getTaskLabel() {
        return taskLabel;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime(String input) {
        return "To be overridden by subclass' methods";
    }

    public String getEndTime(String input) {
        return "To be overridden by subclass' methods";
    }

    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }

    public void markTask() {
        this.mark = "[X]";
    }

    public void unmarkTask() {
        this.mark = "[ ]";
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
