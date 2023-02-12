package grandduke.exception.mark;

import grandduke.exception.GrandException;

public class MarkFormatException extends GrandException {
    @Override
    public String getMessage() {
        return "The Mark/Unmark command is not in the correct format. Please enter only positive digits.";
    }
}
