package duke;

public class TaskParameterException extends DukeException{
    TaskParameterException(String taskName, String additionalMessage){
        super("OOPS!!! There is an invalid input of the parameters for " + taskName + ".\n" +
                additionalMessage);
    }
}
