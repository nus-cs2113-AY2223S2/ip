public class Todo extends Task {
    /**
     * Constructor for Todo task
     * @param taskName  name of Task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Overrides toString() of Object class.
     * @return String indication of task type + whether task is done + task name.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
