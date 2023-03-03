package duke.exception;

import duke.command.CommandInputs;

public class DeadlineParameterException extends TaskParameterException {
    public DeadlineParameterException(){
        super(CommandInputs.ADD_DEADLINE_COMMAND_INPUT, "Please input the dates in the format /by <date>");
    }
}
