package constants;

/**
 * An enum that contains all of the error messages.
 */
public enum ErrorMessage {
  IO_EXCEPTION_ERROR(
    "Named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason."
  ),
  PARSE_INT_ERROR("You have attempted to mark a non-number index"),
  FOUND_NOT_FOUND_EXCEPTION("A file not found exception occured"),
  INVALID_COMMAND("An invalid command has been provided"),
  INVALID_FORMAT("Ensure that your /from comes before your /to"),
  INVALID_NUMBER("Please provide a number when marking or un-marking a task"),
  NO_DESCRIPTION("You did not provide a task description"),
  NO_BY("You have not provided a deadline. Ensure you have the /by keyword"),
  NO_FROM("You did not provide a /from"),
  NO_TASK("Please provide a task"),
  NO_TO("You did not provide a /to");

  public final String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}
