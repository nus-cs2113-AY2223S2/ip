public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public char getTypeIcon() {
        return 'T';
    }

}
