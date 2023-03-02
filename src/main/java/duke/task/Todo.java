package duke.task;

import java.time.LocalDateTime;

import duke.DukeException;

/**
 * Represents task that has no start time and end time.
 */
public class Todo extends Task {

    /**
     * Converter for <code>Todo</code> task. It accepts an input in format of an instruction and 
     * returns the <code>Todo</code> task object.
     * @param instruction Written in format of "todo <code>description</code>", 
     * e.g. "todo Return books".
     * @return <code>Todo</code> object constructed from input <code>instruction</code>.
     * @throws DukeException If the description is not given.
     */
    public static Todo toTodo(String instruction) throws DukeException {
        if(instruction.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        
        return new Todo(instruction);
    }

    /**
     * Constructor for <code>Todo</code> task.
     * @param description Description of <code>Todo</code> task, e.g. "Return books".
     */
    public Todo(String description) {
        super(description, 'T');
    }

    @Override
    public String getTimeBound() {
        return "";
    }

    @Override
    public boolean haveValidDate() {
        return false;
    }

    @Override
    public LocalDateTime getEndTime() {
        return null;
    }
}
