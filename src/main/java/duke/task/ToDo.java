package duke.task;

/**
 * Represents a task that only has a name associated with it
 */
public class ToDo extends Task {
    public ToDo(String name){
        super(name, TaskType.TODO);
    }
    @Override
    public String toString() {
        return super.getTaskPrefixWithName();
    }
}
