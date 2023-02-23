package duke;

public class EventParameterException extends TaskParameterException{
    EventParameterException(){
        super(CommandInputs.ADD_EVENT_COMMAND_INPUT,"Please input the dates in the format /from <date> /to <date>");
    }
}
