package duke.task;

import duke.ui.Symbols;

/**
 * Represents a task, contains task name and a status on completion
 */
public abstract class Task {

    public String taskName;

    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Generates the symbol for the status of task
     *
     * @return 'X' if task is done, else returns ' '
     */
    public String getStatusIcon() {
        if (isDone) {
            return Symbols.PROGRAM_MARK;
        }
        return Symbols.PROGRAM_UNMARK;
    }

    /**
     * Generates the task information to be shown to user
     *
     * @return string containing the task information
     */
    public abstract String getFullTaskDetail();

    /**
     * Marks a task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task to be not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Generates the task information to be stored
     */
    public abstract String getEncodedData();
}
