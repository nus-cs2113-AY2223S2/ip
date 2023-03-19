package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaStorageException;
import io.github.haoyangw.rica.storage.Serializable;

/**
 * Represents a Task for the user. Can be extended further to provide more specialised/
 *   specific Tasks created by the user.
 */
public class Task implements Serializable {
    private static final String TYPE = "T";
    private static final int NUM_OF_SERIALIZED_DATA = 2;
    protected static final String INCOMPLETE_SERIALIZED_OBJECT_STRING = " Incomplete data found in my past memory... Am I getting memory loss?";
    protected static final String DATA_STRING_SEPARATOR = " | ";
    protected static final String DATA_STRING_SEPARATOR_REGEX = "\\s\\|\\s";
    protected static final String UNKNOWN_SERIALIZED_OBJECT_TYPE = " Did you touch my memories? What Tasks am I finding saved within them LOL";
    protected static final String WRONG_SERIALIZED_OBJECT_TYPE = " Hmm this task from my past memory is... Not my type xD";
    protected final String description;

    public Task(String description) {
        this.description = description;
    }

    protected String getType() {
        return Task.TYPE;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Parses a String representation of a Task and re-creates an instance
     *   of corresponding Task(sub-type) with the previously saved state.
     *
     * @param objectData String representation containing values for a Task's state
     *   variables
     * @return Instance of Task with the previously saved state
     * @throws RicaSerializationException If too few state variables were saved
     *   within the String representation, which implies an invalid saved state
     * @throws RicaStorageException If the String represents a Task that Rica doesn't
     *   recognise
     */
    public static Task deserializeObject(String objectData) throws RicaSerializationException, RicaStorageException {
        String[] variables = objectData.split(Task.DATA_STRING_SEPARATOR_REGEX);
        if (variables.length < Task.NUM_OF_SERIALIZED_DATA) {
            throw new RicaSerializationException(Task.INCOMPLETE_SERIALIZED_OBJECT_STRING);
        }
        int FIRST_PARAM = 0;
        String taskType = variables[FIRST_PARAM];
        switch (taskType) {
        case Deadline.TYPE:
            return Deadline.deserializeObject(objectData);
        case Event.TYPE:
            return Event.deserializeObject(objectData);
        case Task.TYPE:
            if (variables.length != Task.NUM_OF_SERIALIZED_DATA) {
                return Todo.deserializeObject(objectData);
            }
            break;
        default:
            throw new RicaStorageException(Task.UNKNOWN_SERIALIZED_OBJECT_TYPE);
        }
        // We only reach this point when the number of variables match Task's, so
        //   we're dealing with a serialized Task String
        int SECOND_PARAM = 1;
        String description = variables[SECOND_PARAM];
        return new Task(description);
    }
    /**
     * Generates a user-friendly String representation of this Task instance
     *   for the user to understand this Task's current state
     *
     * @return String representation of this Task instance's state
     */
    @Override
    public String toString() {
        return this.getDescription();
    }

    /**
     * Generates a String representation of this Task instance so that its state
     *   may be preserved in persistent storage and restored later.
     *
     * @return String representation of this Task Object's state
     */
    @Override
    public String serializeObject() {
        return String.format("%s | %s", this.getType(), this.getDescription());
    }
}
