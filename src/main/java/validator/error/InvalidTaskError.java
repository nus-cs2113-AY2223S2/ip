package validator.error;

public class InvalidTaskError extends Exception {
    String description;

    public InvalidTaskError(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
