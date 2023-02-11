package tasks;
public class Deadline extends Todo {
    private final String TYPE_REPRESENTATION = "D";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getSaveRepresentation() {
        int doneRepresentation = isDone() ? 1 : 0;
        return String.format("%s /// %d /// %s /// %s", TYPE_REPRESENTATION, doneRepresentation, getDescription(), getBy());
    }
    
    public String toString() {
        char mark;
        if (isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return String.format("[%s][%c] %s (by: %s)", TYPE_REPRESENTATION, mark, description, by);
    }
}