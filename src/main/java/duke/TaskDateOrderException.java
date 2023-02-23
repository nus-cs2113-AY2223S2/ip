package duke;

public class TaskDateOrderException extends TaskParameterException{
    TaskDateOrderException(String taskName){
        super(taskName, "You have inputted the dates in an impossible order.");
    }
}
