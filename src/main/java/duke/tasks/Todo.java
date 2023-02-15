package duke.tasks;

public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String taskInformation() {
        return String.format("%s , %s", "todo", super.taskInformation());
    }
}