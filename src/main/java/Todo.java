public class Todo extends Task {
    protected boolean isDone;

    /** Creates a todo-type task, which contains a description */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    /** Returns type of task (todo) */
    @Override
    public String getType() {
        return "todo";
    }

}
