package Tasks;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String getType() {
        return "todo";
    }
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[T][X] " : "[T][ ] ") + this.description;
        return fullSentence;
    }
}
