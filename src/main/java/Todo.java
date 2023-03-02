public class Todo extends Task {

    public String toString() {
        return ("[T]" + super.toString());
    }
    public Todo(String description) {
        super(description);
    }

}