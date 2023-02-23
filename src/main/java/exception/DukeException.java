package exception;

public class DukeException extends Exception {
    String description;

    public DukeException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
