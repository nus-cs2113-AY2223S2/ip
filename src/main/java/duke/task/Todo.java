package duke.task;

import duke.Task;

public class Todo extends Task {
    public Todo(String description, String taskType) {
        super(description, taskType);

    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String textToSave(){
        return "T | " + (super.isDone ? 1 : 0)  + " | " + super.description;
    }
}