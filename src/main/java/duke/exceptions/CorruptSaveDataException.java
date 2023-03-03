package duke.exceptions;

import duke.constants.ExceptionMessageConstants;

public class CorruptSaveDataException extends Exception {
    public CorruptSaveDataException(String savedData) {
        super(ExceptionMessageConstants.EXCEPTION_CORRUPT_DATA + savedData);
    }
}
