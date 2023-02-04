public class DukeException extends Exception {
    DukeException(String message) {
        super("\tDuke Error: " + message);
    }
}
