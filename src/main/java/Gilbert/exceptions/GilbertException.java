package Gilbert.exceptions;

public class GilbertException extends Exception{

    /**
     * Prints the error message accordingly when there is an invalid command by the user
     * @param errorType The error type.
     */
    public static void errorMessage(String errorType) {
        System.out.println(errorType);
    }
}
