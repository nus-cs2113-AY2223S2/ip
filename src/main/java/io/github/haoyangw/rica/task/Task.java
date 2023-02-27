package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaStorageException;
import io.github.haoyangw.rica.storage.Serializable;

public class Task implements Serializable {
    private static final String TYPE = "T";
    private static final int NUM_OF_SERIALIZED_DATA = 2;
    protected static final String INCOMPLETE_SERIALIZED_OBJECT_STRING = " Incomplete data found in my past memory... Am I getting memory loss?";
    protected static final String DATA_STRING_SEPARATOR = " | ";
    protected static final String DATA_STRING_SEPARATOR_REGEX = "\\s\\|\\s";
    protected static final String UNKNOWN_SERIALIZED_OBJECT_TYPE = " What type of task is this... You didn't teach me how to handle it LOL";
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

    public static Task deserializeObject(String objectData) throws RicaSerializationException {
        String[] variables = objectData.split(Task.DATA_STRING_SEPARATOR_REGEX);
        if (variables.length < Task.NUM_OF_SERIALIZED_DATA) {
            throw new RicaSerializationException(Task.INCOMPLETE_SERIALIZED_OBJECT_STRING);
        }
        String taskType = variables[0];
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
        String description = variables[1];
        return new Task(description);
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    @Override
    public String serializeObject() {
        return String.format("%s | %s", this.getType(), this.getDescription());
    }
}
