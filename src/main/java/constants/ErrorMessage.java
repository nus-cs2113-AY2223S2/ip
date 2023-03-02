package constants;

/**
 * This interface will contain most of the error messages that will be used throughout the
 * application.
 */
public interface ErrorMessage {
  String IO_EXCEPTION_ERROR = "Named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.";
  String FOUND_NOT_FOUND_EXCEPTION = "A file not found exception occurred";
  String INVALID_INDEX = "You have provided an invalid index.";
  String INVALID_COMMAND = "An invalid command has been provided";
  String INVALID_INPUT = "A valid command has been provided but the input format is incorrect";
  String NO_DEADLINE_PROVIDED = "No deadline provided";
  String NO_DESCRIPTION_PROVIDED = "No description has been provided";
  String PARSE_INT_ERROR = "You have provided a non-number index\n";
  String UNKNOWN_COMMAND = "An unknown command has been provided";
}
