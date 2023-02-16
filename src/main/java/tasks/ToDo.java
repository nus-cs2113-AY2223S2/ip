package tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString();
    }
}
