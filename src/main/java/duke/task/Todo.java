package duke.task;

import duke.output.Symbols;

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

    public String getSavedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK.SYMBOL)) {
            taskStatus = Symbols.DATA_MARK.SYMBOL;
        } else {
            taskStatus = Symbols.DATA_UNMARK.SYMBOL;
        }
        String fullDetails = String.join(Symbols.DATA_DELIMITER.SYMBOL,
                Symbols.TODO.SYMBOL, taskStatus, taskName);
        return fullDetails;
    }
}
