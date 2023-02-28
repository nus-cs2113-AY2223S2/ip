package genesis;

import exception.InvalidArgumentException;

/**
 * The Validator class is responsible for validating user input for commands that require an index or a description.
 */
public class Validator {
    public Validator() {
    }

    /**
     * Validates the index component of user input.
     *
     * @param contents an array of user input strings
     * @throws InvalidArgumentException if the index is empty
     */
    protected void validateIndex(String[] contents) throws InvalidArgumentException {
        if (contents.length < 2) {
            throw new InvalidArgumentException("Index cannot be empty");
        }
    }

    /**
     * Validates the description component of user input.
     *
     * @param contents an array of user input strings
     * @throws InvalidArgumentException if the description is empty or null
     */
    protected void validateDescription(String[] contents) throws InvalidArgumentException {
        if (contents.length < 2 || contents[1].isBlank()) {
            throw new InvalidArgumentException("The description cannot be empty.");
        }
    }

    /**
     * Validates the keyword component of user input.
     *
     * @param contents an array of user input strings
     * @throws InvalidArgumentException if the keyword is empty or null
     */
    protected void validateKeyword(String[] contents) throws InvalidArgumentException {
        if (contents.length < 2 || contents[1].isBlank()) {
            throw new InvalidArgumentException("The keyword cannot be empty.");
        }
    }
}
