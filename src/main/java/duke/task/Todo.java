package duke.task;

import duke.DukeException;

public class Todo extends Task {

    public static Todo toTodo(String instruction) throws DukeException {
        if(instruction.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        
        return new Todo(instruction);
    }

    public Todo(String description) {
        super(description, 'T');
    }

    @Override
    public String getTimeBound() {
        return "";
    }
}
