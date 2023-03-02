package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    String deadline;
    String taskDescription;
    public Deadline (String taskDescription, String deadline) {
        super(taskDescription + " (by: " + deadline + ")");
        this.deadline = deadline;
        this.taskDescription = taskDescription;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toSaveString(String... taskParameters) {
        return super.toSaveString("D", isDone ? "1" : "0", taskDescription, deadline);
    }
}
