package tasks;

public class ToDo extends Task {
    protected TaskType taskType = TaskType.TODO;

    public ToDo(String description) throws EmptyDescriptionException{
        super(description);
    }

    @Override
    public String getTaskSymbol() {
        // T for tasks.ToDo
        return "T";
    }
}
