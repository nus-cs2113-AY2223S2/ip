package Tasks;
public class Todo extends Task {
    /**
     * Constructor for todo
     *
     * @param description string of description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Getter for todo type
     *
     * @return string of todo
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * Getter for full description of a todo
     *
     * @return string with todo status and description
     */
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[T][X] " : "[T][ ] ") + this.description;
        return fullSentence;
    }
}
