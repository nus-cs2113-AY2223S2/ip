package constants;

/**
 * An enum that contains all the error messages.
 */
public enum ErrorMessage {
  IO_EXCEPTION_ERROR(
          "Named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason."
  ),
  PARSE_INT_ERROR("You have attempted to mark a non-number index"),
  NO_DESCRIPTION("You did not provide a task description"),
  INVALID_NUMBER("Please provide a number when marking or un-marking a task"),
  INVALID_COMMAND("An invalid command has been provided"),
  FOUND_NOT_FOUND_EXCEPTION("A file not found exception occurred");

  public final String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}
