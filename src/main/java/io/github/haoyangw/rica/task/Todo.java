package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaTaskException;

public class Todo extends Task {
    private static final String MISSING_DESCRIPTION_ERROR = " Remember to provide a description of the todo task you're adding y'know!";
    private static final int NUM_OF_SERIALIZED_DATA = 3;
    protected static final String TYPE = "T";
    private final boolean isDone;

    protected Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public Todo(String description) {
        this(description, false);
    }

    private static String getDescriptionOf(String command) {
        String[] parameters = command.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            int FIRST_WORD_OF_DESCRIPTION = 1;
            if (i != FIRST_WORD_OF_DESCRIPTION) {
                descriptionBuilder.append(" ");
            }
            descriptionBuilder.append(parameters[i]);
        }
        return descriptionBuilder.toString();
    }

    protected boolean getIsDone() {
        return this.isDone;
    }

    @Override
    protected String getType() {
        return Todo.TYPE;
    }

    public static Todo create(String command) throws RicaTaskException {
        String description = Todo.getDescriptionOf(command);
        if (description.isBlank()) {
            throw new RicaTaskException(Todo.MISSING_DESCRIPTION_ERROR);
        }
        return new Todo(description);
    }

    public static Todo deserializeObject(String objectData) {
        String[] variables = objectData.split(Task.DATA_STRING_SEPARATOR_REGEX);
        if (variables.length < Todo.NUM_OF_SERIALIZED_DATA) {
            throw new RicaSerializationException(Task.INCOMPLETE_SERIALIZED_OBJECT_STRING);
        }
        if (!variables[0].equals(Todo.TYPE)) {
            throw new RicaSerializationException(Task.WRONG_SERIALIZED_OBJECT_TYPE);
        }
        int SECOND_SAVED_DATA = 1;
        String description = variables[SECOND_SAVED_DATA];
        int THIRD_SAVED_DATA = 2;
        boolean isDone = Boolean.parseBoolean(variables[THIRD_SAVED_DATA]);
        return new Todo(description, isDone);
    }

    @Override
    public String serializeObject() {
        String taskData = super.serializeObject();
        return taskData + Task.DATA_STRING_SEPARATOR + this.getIsDone();
    }

    public Todo setDone(boolean isDone) {
        return new Todo(super.getDescription(), isDone);
    }

    @Override
    public String toString() {
        String boxContent = this.getIsDone() ? "X" : " ";
        return String.format("[%s][%s] %s", this.getType(), boxContent, super.toString());
    }

}
