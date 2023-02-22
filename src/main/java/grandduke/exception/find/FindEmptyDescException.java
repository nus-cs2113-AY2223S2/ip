package grandduke.exception.find;

import grandduke.exception.GrandException;

public class FindEmptyDescException extends GrandException {

    @Override
    public String getMessage() {
        return "Please enter a keyword to search for";
    }
}
