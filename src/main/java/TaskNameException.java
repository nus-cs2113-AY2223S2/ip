public class TaskNameException extends DukeException{
    TaskNameException(String taskName){
        super("OOPS!!! The description of a " + taskName + " cannot be empty.");
    }
}
