package chronos.tasktype;

import chronos.savehandler.Save;


/**
 * Represents a Todo type of task, subclass of Task
 */
public class Todo extends Task{
    /**
     * Constructs a new Todo object
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), "", "", "");
    }
}
