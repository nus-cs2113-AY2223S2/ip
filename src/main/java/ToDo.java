public class ToDo extends Task {

    public static final String TODO_LABEL = "T";

    public ToDo(String description) {
        super(description);
        increaseCounter();
    }
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[" + TODO_LABEL + "][" + getStatus() + "] " + description;
    }
}
