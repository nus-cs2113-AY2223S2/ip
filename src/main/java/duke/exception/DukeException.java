package duke.exception;

public class DukeException extends Exception {
    protected static String errorMessage;
    public DukeException(String e) {
        errorMessage = e;
    }

    public static void showError() {
        System.out.println(errorMessage);
    }
}