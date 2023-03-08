package grandduke.exception.delete;

import grandduke.exception.GrandException;

public class DeleteFormatException extends GrandException {

    @Override
    public String getMessage() {
        return "The delete command is not in the correct format. Please enter only positive digits.";
    }

}
