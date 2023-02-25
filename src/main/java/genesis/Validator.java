package genesis;

import exception.InvalidArgumentException;

/**
 * The Validator class is responsible for validating user input for commands that require an index or a description.
 */
public class Validator {
    public Validator() {
    }

    /**
     * Validates that an index is provided in the user input.
     *
     * @param contentArr An array of strings containing user input.
     * @throws InvalidArgumentException If the index is missing or empty.
     */
    protected void validateIndex(String[] contentArr) throws InvalidArgumentException {
        if (contentArr.length < 2) {
            throw new InvalidArgumentException("Index cannot be empty");
        }
    }

    /**
     * Validates that a description is provided in the user input.
     *
     * @param contentArr An array of strings containing user input.
     * @throws InvalidArgumentException If the description is missing or empty.
     */
    protected void validateDescription(String[] contentArr) throws InvalidArgumentException {
        if (contentArr.length < 2 || contentArr[1].isBlank()) {
            throw new InvalidArgumentException("The description cannot be empty.");
        }
    }
}
