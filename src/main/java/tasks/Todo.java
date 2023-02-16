package tasks;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName, false, "[T]");
    }

    public String toString() {
        return taskSign + super.toString();
    }

}//tasks.todo class ends here