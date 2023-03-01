package duke.tasks;
/**
 * Represents a Todo task in Duke, which is a subclass of Task.
 */
public class Todo extends Task {
    /**
     * Todo constructor which calls the superclass constructor
     *
     * @param name        Name of Todo task
     * @param isCompleted Completion status of task
     */
    public Todo(String name, Boolean isCompleted) {
        super(name, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTextFileFormat(){
        return "todo/" + name + "/" + isCompleted;
    }
}
