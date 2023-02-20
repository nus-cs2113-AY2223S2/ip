package duke.task;

import duke.ui.Symbols;

public abstract class Task {
    public String taskName;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return Symbols.PROGRAM_MARK;
        }
        return Symbols.PROGRAM_UNMARK;
    }

    public abstract String getFullTaskDetail();

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getEncodedData();
}
