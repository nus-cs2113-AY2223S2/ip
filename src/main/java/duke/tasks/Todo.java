package duke.tasks;

import duke.exceptions.*;

import static duke.exceptions.UserInputException.inputExceptionType.EMPTY_TASK_DESCRIPTION;

public class Todo extends Task{

    public Todo(String description) throws DukeException{
        super(description);
        if(description.equals("")){
            throw new UserInputException(EMPTY_TASK_DESCRIPTION,"todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
