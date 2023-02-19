package tasks;
public class Todo extends Task {
    protected boolean isDone;
    private final String TYPE_REPRESENTATION = "T";

    public String getTypeRepresentation() {
        return TYPE_REPRESENTATION;
    }

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTypeRep() {
        return TYPE_REPRESENTATION;
    }

    public String getSaveRepresentation() {
        int doneRepresentation = isDone() ? 1 : 0;
        return String.format("%s /// %d /// %s", TYPE_REPRESENTATION, doneRepresentation, getDescription());
    }
    
    public String toString() {
        char mark;
        if (isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return String.format("[%s][%c] %s", TYPE_REPRESENTATION, mark, description);
    }
}