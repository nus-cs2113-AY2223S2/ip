package tusky.tasks;

import tusky.tasks.Task;
import tusky.tasks.TaskType;

public class Deadline extends Task {

    protected String by;
    public Deadline(String isDone, String description, String by) throws EmptyDescriptionException{
        super(isDone, description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }


    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskSymbol() {
        // D for tusky.tasks.Deadline
        return "D";
    }

    @Override
    public String toString(){
        return String.format("%s (by: %s)", description, by);
    }
}
