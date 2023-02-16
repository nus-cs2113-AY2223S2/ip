package duke.task;

import duke.ui.Symbols;

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
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        return String.join(Symbols.DATA_DELIMITER, Symbols.DEADLINE, taskStatus, taskName, deadline);
        // returns full details of task
    }

}
