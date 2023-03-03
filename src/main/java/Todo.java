/**
 * Represents a todo task.
 * A <code>Todo</code> object corresponds to a task with contents and progress of the task.
 */

public class Todo extends Task {
    Todo(String userInput) {
        super(userInput);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[T][O] " + getContents();
        }
        return "[T][ ] " + getContents();
    }

    @Override
    public String showTask(){
        if (getIsDone()) {
            return "[T][O] " + getContents();
        }
        return "[T][ ] " + getContents();
    }
}
