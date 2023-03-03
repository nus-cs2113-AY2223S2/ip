package duke.exception;

import duke.command.CommandInputs;

public class EventParameterException extends TaskParameterException{
    public EventParameterException(){
        super(CommandInputs.ADD_EVENT_COMMAND_INPUT,"Please input the dates in the format /from <date> /to <date>");
    }
}
