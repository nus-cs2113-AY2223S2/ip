package duke.task;

import duke.ui.Symbols;

/**
 * Task without any date/time attached to it
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Generates the task information to be shown to user
     *
     * @return string containing the task information in the format: [T][] taskName
     */
    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[T][" + getStatusIcon() + "] " + taskName;
        return taskDetail;
    }

    /**
     * Generates the task information to be stored
     *
     * @return string containing the encoded task information in the format: T | 0 | taskName
     */
    @Override
    public String getEncodedData() {
        String taskStatus;
        if (getStatusIcon().equals(Symbols.PROGRAM_MARK)) {
            taskStatus = Symbols.DATA_MARK;
        } else {
            taskStatus = Symbols.DATA_UNMARK;
        }
        return String.join(Symbols.ENCODE_DATA_DELIMITER, Symbols.TODO, taskStatus, taskName);
    }
}
