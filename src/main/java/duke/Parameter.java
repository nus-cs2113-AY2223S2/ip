package duke;

public class Parameter {
    private final ParameterType type;
    private final String value;

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

    public ParameterType getParameterType() {
        return this.type;
    }

    public String getParameterValue() {
        return this.value;
    }
}

enum ParameterType {
    DEADLINE, EVENT_START, EVENT_END
}
