package Exceptions;

public class NonPositiveNumberException extends DukeException {
    public NonPositiveNumberException() {
        super("Oops! Only positive integers can be used as the index!");
    }

    public NonPositiveNumberException(Throwable err) {
        super("Oops! Only positive integers can be used as the index!", err);
    }
}
