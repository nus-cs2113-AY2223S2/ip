package duke;

public class TaskDateFormatException extends TaskParameterException{
    TaskDateFormatException(String taskName){
        super(taskName, "Please input the date in the format yyyy-mm-dd.");
    }
}
