package duke;

/**
 * A class representing a parameter in a command
 */
public class Parameter {
    private final ParameterType type;
    private final String value;

    /**
     * Constructor for a parameter object
     * @param input a substring of the command string representing a parameter flag and its value
     * @throws IllegalParameterException parameter flag is invalid or its value is missing
     */
    public Parameter(String input) throws IllegalParameterException {
        String parameterType = input.split(" ", 2)[0];

        switch (parameterType) {
            case "by":
                this.type = ParameterType.DEADLINE;
                break;
            case "from":
                this.type = ParameterType.EVENT_START;
                break;
            case "to":
                this.type = ParameterType.EVENT_END;
                break;
            default:
                throw new IllegalParameterException(IllegalParameterExceptionType.PARAMETER_DOES_NOT_EXIST, parameterType);
        }

        try {
            this.value = input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException(IllegalParameterExceptionType.MISSING_VALUE, parameterType);
        }
    }

    /**
     * Getter for parameter type
     * @return the parameter type
     */
    public ParameterType getParameterType() {
        return this.type;
    }

    /**
     * Getter for parameter value
     * @return the parameter value
     */
    public String getParameterValue() {
        return this.value;
    }
}

enum ParameterType {
    DEADLINE, EVENT_START, EVENT_END
}
