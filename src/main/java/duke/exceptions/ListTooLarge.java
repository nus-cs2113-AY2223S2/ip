package duke.exceptions;

public class ListTooLarge extends Exception {
    public ListTooLarge() {
        super("Can't Add anymore items, the list has reached its max size");
    }
}
