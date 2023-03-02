package tusky.tasks;

import tusky.exceptions.EmptyDescriptionException;

/**
 * Class that represents a todo task.
 * A todo task has no date/time attached to it.
 */
public class ToDo extends Task {

    public ToDo(String isDone, String description) throws EmptyDescriptionException {
        super(isDone, description);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String getTaskSymbol() {
        // T for tusky.tasks.ToDo
        return "T";
    }
}
