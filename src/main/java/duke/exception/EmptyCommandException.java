package duke.exception;

/**
 * The Exception that is being thrown when the only the task command such as todo, deadline and event is given
 * but there are no inputs after that such as the description of the timing
 */
public class EmptyCommandException extends DukeException {
}
