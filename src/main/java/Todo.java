public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T][" + super.getStatusIcon() + "] " + super.description);
    }

    @Override
    public String fileFormat() {
        return (String.format("T|%b|%s", super.isDone, this.description));
    }
}
