package duke.task;
import duke.Task;

public class Todo extends Task {
<<<<<<< HEAD

    public Todo(String description, String taskType){
=======
    public Todo(String description, String taskType) {
>>>>>>> branch-Level-7
        super(description, taskType);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveText() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }

    @Override
    public String saveText() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }
}


