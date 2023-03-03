package btb.exceptions;

public class NotIntegerTaskNumberException extends DukeException {
    private final String description;
    public NotIntegerTaskNumberException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return "\t Task " + description + " is not an integer task number ╰（‵□′）╯.\n" +
                "\t Please enter a valid task number.";
    }
}
