package validator;

import constants.ErrorMessage;
import validator.error.InvalidTaskError;

public class Validator {

    /**
     * A validator function containing all the guard clauses to validate the
     * deadline tasks.
     *
     * @param index The index of the description.
     * @throws InvalidTaskError If an invalid deadline task has been provided.
     */
    public void validateDeadlineTask(int index) throws InvalidTaskError {
        if (index == -1) {
            throw new InvalidTaskError(ErrorMessage.NO_BY.message);
        }

        if (index == 0) {
            throw new InvalidTaskError(ErrorMessage.NO_TASK.message);
        }
    }

    /**
     * A validator function containing all the guard clauses to validate the
     * deadline tasks.
     *
     * @param indexOfFrom Index of the /from.
     * @param indexOfTo   Index of the /to.
     * @throws InvalidTaskError If an invalid deadline task has been provided.
     */
    public void validateEventTask(int indexOfFrom, int indexOfTo)
            throws InvalidTaskError {
        if (indexOfFrom == -1) {
            throw new InvalidTaskError(ErrorMessage.NO_FROM.message);
        }

        if (indexOfTo == -1) {
            throw new InvalidTaskError(ErrorMessage.NO_TO.message);
        }

        if (indexOfFrom > indexOfTo) {
            throw new InvalidTaskError(
                    ErrorMessage.INVALID_FORMAT.message
            );
        }
    }
}
