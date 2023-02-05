package dev.joulev.archduke.exceptions;

import dev.joulev.archduke.datetime.DateTime;

public class UserInputException extends ArchdukeException {
    public enum UserInputExceptionCode {
        TASK_DESCRIPTION_IS_EMPTY, TODO_FROM_IS_EMPTY, TODO_TO_IS_EMPTY, DEADLINE_BY_IS_EMPTY,
        INDEX_IS_NOT_A_NUMBER, INDEX_IS_OUT_OF_BOUNDS, MALFORMED_DATETIME
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
        case INDEX_IS_NOT_A_NUMBER:
            return "The index of the task must be a number.";
        case INDEX_IS_OUT_OF_BOUNDS:
            try {
                int taskCount = Integer.parseInt(payload);
                if (taskCount == 1) {
                    return String.format(
                            "The index of the task is out of bounds. There is 1 task in the list.");
                }
                return String.format(
                        "The index of the task is out of bounds. There are %d tasks in the list.",
                        taskCount);
            } catch (NumberFormatException e) {
                throw new UnknownException("UserInputException; code = NumberFormatException");
            }
        case MALFORMED_DATETIME:
            return String.format(
                    "The date and time you entered is not in the correct format. The only allowed format is %s. (Example: %s)",
                    DateTime.INPUT_PATTERN, DateTime.EXAMPLE);
        default:
            throw new UnknownException("UserInputException; code = " + code);
        }
    }
}
