package task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTask() {
        return this.description.split(" ",2)[1];
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString() + getTask());
    }
}
