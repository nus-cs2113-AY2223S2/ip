public class Todo extends Task {
    private static final String TYPE = "T";
    protected final boolean isDone;

    public Todo(String description) {
        this(description, false);
    }
    private Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public static Todo create(String command) {
        String[] parameters = command.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            if (i != 1) {
                descriptionBuilder.append(" ");
            }
            descriptionBuilder.append(parameters[i]);
        }
        String description = descriptionBuilder.toString();
        if (description.isBlank()) {
            return null;
        }
        return new Todo(description);
    }

    protected boolean getIsDone() {
        return this.isDone;
    }

    protected String getType() {
        return Todo.TYPE;
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
