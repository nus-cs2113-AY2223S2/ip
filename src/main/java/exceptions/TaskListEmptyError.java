package exceptions;

/**
 * A <code>Throwable</code> exception that occurs when
 * {@link command.CommandType#MARK}, {@link command.CommandType#UNMARK},
 * {@link command.CommandType#DELETE} and {@link command.CommandType#FIND}
 * commands are being carried out on an empty.
 */
public class TaskListEmptyError extends Exception{

    /**
     * Class constructor.
     */
    public TaskListEmptyError() {
    }
}
