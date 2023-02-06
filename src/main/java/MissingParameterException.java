public class MissingParameterException extends Exception {
    /**
     * Exception which returns an error message when there are missing parameter(s) in task operations.
     *
     * @return An error message when user provides incomplete/missing parameters in task operations
     */
    public String missingParamDesc() {
        return "Task description parameter is missing";
    }

    public String missingParamStart() {
        return "Task from parameter is missing";
    }

    public String missingParamEnd() {
        return "Task to parameter is missing";

    }

    public String missingParamBy() {
        return "Task by parameter is missing";
    }

}
