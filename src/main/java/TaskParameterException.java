public class TaskParameterException extends DukeException{
    TaskParameterException(String taskName){
        super("OOPS!!! There is an invalid input of the parameters for " + taskName + ".");
    }
}
