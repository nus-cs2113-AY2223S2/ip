public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        if (super.IsCompleted()) {
            return ".[T][X] " + super.GetTaskName();
        } else {
            return ".[T][ ] " + super.GetTaskName();
        }
    }

}
