public class Todo extends Task {
    private boolean done;

    public Todo(String name) {
        super(name);
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        char marker = ' ';
        if (this.done) {
            marker = 'X';
        }
        return "[" + marker + "] " + this.getName();
    }
}
