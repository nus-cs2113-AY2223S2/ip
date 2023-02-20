package duke.task;

import duke.ui.Symbols;

public class Todo extends Task {
    // tasks without any date/time attached to it

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[T][" + getStatusIcon() + "] " + taskName;
        return taskDetail;
    }

    @Override
    public String getEncodedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        return String.join(Symbols.ENCODE_DATA_DELIMITER, Symbols.TODO, taskStatus, taskName);
        // returns full details of task
    }
}
