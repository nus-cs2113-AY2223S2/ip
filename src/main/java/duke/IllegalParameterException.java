package duke;

public class IllegalParameterException extends Exception {
    public IllegalParameterExceptionType type;
    public String value;

    public IllegalParameterException(IllegalParameterExceptionType type, String value) {
        this.type = type;
        this.value = value;
    }
}

enum IllegalParameterExceptionType {
    PARAMETER_DOES_NOT_EXIST, MISSING_VALUE
}
