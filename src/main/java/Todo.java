public class Todo {
    private String name;
    private boolean done;

    public Todo(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "[" + marker + "] " + this.name;
    }
}
