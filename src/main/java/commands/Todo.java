package commands;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return ("[T]" + super.toString() + this.description.split(" ",2)[1]);
    }
}
