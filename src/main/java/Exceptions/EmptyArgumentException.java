package Exceptions;

public class EmptyArgumentException extends DukeException {
    public EmptyArgumentException(String emptyArgument) {
        super("Oops! " + emptyArgument + " cannot be empty!");
    }

    public EmptyArgumentException(String emptyArgument,Throwable err) {
        super("Oops! " + emptyArgument + " cannot be empty!", err);
    }
}
