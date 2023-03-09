package duke.exceptions;

import duke.Duke;
public class UserInputException extends DukeException{
    public enum inputExceptionType {
        INVALID_TASK_TYPE, EMPTY_TASK_DESCRIPTION, EMPTY_DEADLINE_TIME,EMPTY_EVENT_START_TIME,EMPTY_EVENT_END_TIME
    }
    inputExceptionType exceptionType;
    String exceptionMessage;
    public UserInputException(inputExceptionType exceptionType) {
        super("Exception of type: "+exceptionType+" occurred.");
        this.exceptionType = exceptionType;
    }
    public UserInputException(inputExceptionType exceptionType, String exceptionMessage) {
        super("Exception of type: "+exceptionType+" occurred, with message" + exceptionMessage);
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String ProduceErrorMessage() {
        switch(exceptionType){
        case INVALID_TASK_TYPE:
            return("OOPS!!! I'm sorry, but I don't know what that means :-(");
        case EMPTY_TASK_DESCRIPTION:
            return("OOPS!!! The description of a "+exceptionMessage+" cannot be empty.");
        case EMPTY_DEADLINE_TIME:
            return("OOPS!!! The time of deadline cannot be empty.");
        case EMPTY_EVENT_START_TIME:
            return("OOPS!!! The start time of event cannot be empty.");
        case EMPTY_EVENT_END_TIME:
            return("OOPS!!! The end time of event cannot be empty.");
        default:
            return("Unknown error, please try again.");
        }

    }
}
