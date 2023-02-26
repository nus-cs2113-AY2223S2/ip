package tusky.tasks;

import tusky.exceptions.EmptyDescriptionException;

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
