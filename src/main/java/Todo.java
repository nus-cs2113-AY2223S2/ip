public class Todo extends Task {
    protected boolean isDone;
    private final String typeRepresentation = "T";

    public String getTypeRepresentation() {
        return typeRepresentation;
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
    
    public String toString() {
        char mark;
        if (isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return String.format("[%s][%c] %s", typeRepresentation, mark, description);
    }
}