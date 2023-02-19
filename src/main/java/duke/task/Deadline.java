package duke.task;

import duke.output.Symbols;

public class Deadline extends Task {
    // tasks that need to be done before a specific date/time
    public String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[D][" + getStatusIcon() + "] " + taskName + " (by: " + deadline + ")";
        return taskDetail;
    }

    public String getSavedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK.SYMBOL)) {
            taskStatus = Symbols.DATA_MARK.SYMBOL;
        } else {
            taskStatus = Symbols.DATA_UNMARK.SYMBOL;
        }
        String fullDetails = String.join(Symbols.DATA_DELIMITER.SYMBOL,
                Symbols.DEADLINE.SYMBOL, taskStatus, taskName, deadline);
        return fullDetails;
    }

}
