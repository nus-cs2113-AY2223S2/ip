package duke;

public class IllegalCommandException extends Exception {
    public IllegalCommandExceptionType type;
    public String value;
    public IllegalCommandException(IllegalCommandExceptionType type, String value) {
        this.type = type;
        this.value = value;
    }
}

enum IllegalCommandExceptionType {
    COMMAND_DOES_NOT_EXIST, MISSING_VALUE, MISSING_PARAMETER
}
