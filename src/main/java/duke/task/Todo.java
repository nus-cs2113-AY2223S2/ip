package duke.task;

/**
 * <h1>Todo</h1>
 * The Todo class is the child of the Task class.
 * It represents the Todo tasks.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String getDescription() {
        return "[T] " + super.getDescription();
    }
}
