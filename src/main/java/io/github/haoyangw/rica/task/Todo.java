package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaTaskException;

public class Todo extends Task {
    private static final String MISSING_DESCRIPTION_ERROR = " Remember to provide a description of the todo task you're adding y'know!";
    private static final String TYPE = "T";
    protected final boolean isDone;

    private Todo(String description, boolean isDone) {
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
            if (i != 1) {
                descriptionBuilder.append(" ");
            }
            descriptionBuilder.append(parameters[i]);
        }
        return descriptionBuilder.toString();
    }

    protected boolean getIsDone() {
        return this.isDone;
    }

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

    public Todo setDone(boolean isDone) {
        return new Todo(this.getDescription(), isDone);
    }

    @Override
    public String toString() {
        String boxContent = this.getIsDone() ? "X" : " ";
        return String.format("[%s][%s] %s", this.getType(), boxContent, super.toString());
    }

}
