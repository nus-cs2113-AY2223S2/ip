public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String fullDescription() {
        String fullSentence;
        fullSentence = (isDone ? "[T][X] " : "[T][ ] ") + this.description;
        return fullSentence;
    }
}
