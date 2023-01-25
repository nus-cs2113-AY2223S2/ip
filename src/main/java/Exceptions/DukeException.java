package Exceptions;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(
            "\t----------------------------------------------------\n\t" 
            + errorMessage + 
            "\n\t----------------------------------------------------\n"
        );
    }

    public DukeException(String errorMessage,Throwable err) {
        super(
            "\t----------------------------------------------------\n\t" 
            + errorMessage +
            "\n\t----------------------------------------------------\n",
            err
        );
    }
}
