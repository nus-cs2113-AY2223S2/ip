package duke;
import duke.exceptions.ToDoException;
public class ToDo extends Task{
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
