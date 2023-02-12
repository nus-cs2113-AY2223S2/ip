package duke.task;

import duke.output.Symbols;

public abstract class Task {
    public String taskName;
    public boolean isDone;
    public static int totalTasks = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public static void incrementTotalTasks() {
        totalTasks += 1;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    public String getStatusIcon() {
        if (isDone) {
            return Symbols.PROGRAM_MARK.SYMBOL;
        }
        return Symbols.PROGRAM_UNMARK.SYMBOL;
    }

    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[" + getStatusIcon() + "] " + taskName;
        return taskDetail;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getSavedData();
}
