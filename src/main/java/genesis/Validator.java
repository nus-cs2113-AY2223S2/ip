package genesis;

import exception.InvalidArgumentException;

public class Validator {
    public Validator() {
    }

    protected void validateIndex(String[] contents) throws InvalidArgumentException {
        if (contents.length < 2) {
            throw new InvalidArgumentException("Index cannot be empty");
        }
    }

    protected void validateDescription(String[] contents) throws InvalidArgumentException {
        if (contents.length < 2 || contents[1].isBlank()) {
            throw new InvalidArgumentException("The description cannot be empty.");
        }
    }

    protected void validateKeyword(String[] contents) throws InvalidArgumentException {
        if(contents.length < 2 || contents[1].isBlank()){
            throw new InvalidArgumentException("The keyword cannot be empty.");
        }
    }
}
