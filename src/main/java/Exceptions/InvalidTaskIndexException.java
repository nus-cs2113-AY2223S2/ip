package Exceptions;

public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(int tasksCount, Throwable err) {
        super("Oops! The index inputted is out of range! The tasks list currently has " + tasksCount + " elements", err);
    }
}
