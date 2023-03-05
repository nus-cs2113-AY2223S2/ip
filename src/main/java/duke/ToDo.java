package duke;
import duke.exceptions.ToDoException;

/**
 * Represents a task that is of to-do type.
 */
public class ToDo extends Task{
    /**
     * Sets the description and done status of the to-do task.
     *
     * @param description description of the to-do task.
     * @param isDone done status of the to-do task.
     * @throws ToDoException if description field of the to-do task is empty.
     */
    public ToDo(String description, boolean isDone) throws ToDoException{
        super(description,isDone);
        if(description.length() == 0){
            throw new ToDoException();
        }
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [T][" + getStatusIcon() + "] " + description;
    }
}
