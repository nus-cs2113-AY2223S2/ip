package sage.exceptions;

/**
 * Exception which returns an error message when there are missing parameter(s) in task operations.
 */
public class MissingParameterException extends Exception {

    public void missingParamDesc() {
        System.out.println("Task \"description\" parameter is missing");
    }

    public void missingParamStart() {
        System.out.println("Task \"from\" parameter is missing");
    }

    public void missingParamEnd() {
        System.out.println("Task \"to\" parameter is missing");

    }

    public void missingParamBy() {
        System.out.println("Task \"by\" parameter is missing");
    }

    public void missingParamTaskIndex() {
        System.out.println("\"index\" parameter is missing");
    }

    public void missingParamKeyword() {
        System.out.println("\"Keywords\" parameter is missing");
    }
}
