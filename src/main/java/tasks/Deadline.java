package tasks;

public class Deadline extends Task {
    protected TaskType taskType = TaskType.DEADLINE;

    protected String by;
    public Deadline(String description, String by) throws EmptyDescriptionException{
        super(description);
        this.by = by;
    }


    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskSymbol() {
        // D for tasks.Deadline
        return "D";
    }

    @Override
    public String toString(){
        return String.format("%s (by: %s)", description, by);
    }
}
