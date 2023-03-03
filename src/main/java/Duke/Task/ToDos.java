package Duke.Task;

import Duke.Exception.EmptyToDoException;
import Duke.Ui;

public class ToDos extends Task {
    private static final int TODOS_INDEX = 5;
    private String taskLabel = "[T]";

    public ToDos(String input) throws EmptyToDoException {
        super(input.substring(TODOS_INDEX)); // for ToDos tasks, description = input
        super.setTaskLabel(taskLabel);
        if (input.substring(TODOS_INDEX).isEmpty()) {
            throw new EmptyToDoException();
        }
    }
    @Override
    public String toString() {
        return this.taskLabel + this.mark + " " + this.description;
    }
}
