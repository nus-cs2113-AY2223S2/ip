package alltasks;

public class Todo extends Task{

    public Todo(String descriptive) {
        super(descriptive);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }
}
