package chronos.tasktype;

import chronos.savehandler.Save;


/**
 * Represents the Deadline type task
 */
public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        if (due == null) {
            throw new IllegalArgumentException("Please provide a due date.");
        }
        this.due = due;
    }

    public Deadline(String description, String due, boolean isDone) {
        super(isDone, description);
        if (due == null) {
            throw new IllegalArgumentException("Please provide a due date.");
        }
        this.due = due;
    }


    public String getDue() {
        return due;
    }


    public String toString() {
        return String.format("[D] %s (DUE: %s)", super.toString(), getDue());
    }

    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), due, "", "");
    }
}
