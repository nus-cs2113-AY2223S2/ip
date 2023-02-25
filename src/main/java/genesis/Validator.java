package genesis;

import exception.InvalidArgumentException;

public class Validator {
    public Validator() {
    }

    protected void validateIndex(String[] contentArr) throws InvalidArgumentException {
        if (contentArr.length < 2) {
            throw new InvalidArgumentException("Index cannot be empty");
        }
    }

    protected void validateDescription(String[] contentArr) throws InvalidArgumentException {
        if (contentArr.length < 2 || contentArr[1].isBlank()) {
            throw new InvalidArgumentException("The description cannot be empty.");
        }
    }
}
