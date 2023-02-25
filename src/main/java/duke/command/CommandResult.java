package duke.command;

import java.util.List;

import duke.tasklist.task.Task;

public class CommandResult {
    private final String feedback;
    private final List<Task> relevantTasks;

    public CommandResult(String feedback) {
        this(feedback, null);
    }

    public CommandResult(String feedback, List<Task> relevantTasks) {
        this.feedback = feedback;
        this.relevantTasks = relevantTasks;
    }


    public String getFeedback() {
        return feedback;
    }

    public List<Task> getRelevantTasks() {
        return relevantTasks;
    }
}
