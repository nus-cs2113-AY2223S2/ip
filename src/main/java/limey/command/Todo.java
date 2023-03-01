package limey.command;

public class Todo extends Task{
    public Todo(String taskName){
        super(taskName);
    }
    /**
     * Returns identity of the current task including the following
     * - task type [T] for todo
     * - [X] or [ ] for marked or unmarked tasks
     * - task name
     *
     * @return tasks identity.
     */
    @Override
    public String getTaskIdentity() {
        String todoSymbol = "[T]";
        return todoSymbol + super.getTaskIdentity();
    }
}
