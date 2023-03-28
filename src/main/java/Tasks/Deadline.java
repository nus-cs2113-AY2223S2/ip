package Tasks;

import Tasks.Task;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskName, boolean isDone, int index, String deadline) {
        super(taskName, isDone, index);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
