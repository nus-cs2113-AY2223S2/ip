package duke;

public class DeadlineParameterException extends TaskParameterException{
    DeadlineParameterException(){
        super(CommandInputs.ADD_DEADLINE_COMMAND_INPUT, "Please input the dates in the format /by <date>");
    }
}
