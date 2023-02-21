package duke.taskTypes;

import duke.task.Task;

public class Todo extends Task {
    @Override
    public void setTodo() {
        super.setTodo();
    }

    public Todo(String description) {
        super(description);
        this.setTodo();
    }
}
