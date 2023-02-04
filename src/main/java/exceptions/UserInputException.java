package exceptions;

public class UserInputException extends ArchdukeException {
    public enum UserInputExceptionCode {
        TASK_DESCRIPTION_IS_EMPTY, TODO_FROM_IS_EMPTY, TODO_TO_IS_EMPTY, DEADLINE_BY_IS_EMPTY
    }

    UserInputExceptionCode code;
    String payload;

    public UserInputException(UserInputExceptionCode code) {
        super("User input is invalid with code " + code + ".");
        this.code = code;
    }

    public UserInputException(UserInputExceptionCode code, String payload) {
        super("User input is invalid with code " + code + ".");
        this.code = code;
        this.payload = payload;
    }

    public UserInputException(UserInputExceptionCode code, String payload, String message) {
        super("User input is invalid with code " + code + ": " + message);
        this.code = code;
        this.payload = payload;
    }

    public String getErrorString() throws UnknownException {
        switch (code) {
        case TASK_DESCRIPTION_IS_EMPTY:
            return "The description of a task cannot be empty.";
        case TODO_FROM_IS_EMPTY:
            return "The \"from\" field of a todo item cannot be empty.";
        case TODO_TO_IS_EMPTY:
            return "The \"to\" field of a todo item cannot be empty.";
        case DEADLINE_BY_IS_EMPTY:
            return "The \"by\" field of a deadline item cannot be empty.";
        default:
            throw new UnknownException("UserInputException; code = " + code);
        }
    }
}
