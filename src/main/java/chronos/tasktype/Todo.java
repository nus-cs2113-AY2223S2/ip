package chronos.tasktype;

import chronos.exceptions.ChronosExceptions;
import chronos.savehandler.Save;


/**
 * Represents a Todo type of task, subclass of Task
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object
     *
     * @param description The description of the task
     * @throws ChronosExceptions if the description field is left empty
     */
    public Todo(String description) {
        super(description);
        try {
            if (description == null) {
                throw new ChronosExceptions(null);
            }
        } catch (ChronosExceptions error) {
            System.err.println("THIS CANNOT BE EMPTY. REMOVE THIS TASK AND ENTER 'help' TO VIEW PROPER FORMAT");
        }
    }

    /**
     * Creates a new instance of the {@code Todo} class with the specified completion status
     * and description.
     *
     * @param isDone      whether the task is completed (true) or not (false)
     * @param description a string describing the task
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the {@code Todo} task, including its
     * task type (enclosed in square brackets) and its description and completion status.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Creates a new instance of the {@code Save} class, using the current task's
     * properties (description and completion status) and the specified task type.
     *
     * @param taskType the type of task to be saved (e.g. "todo", "deadline", "event")
     * @return a new instance of the {@code Save} class representing the current task
     */
    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), "", "", "");
    }
}
