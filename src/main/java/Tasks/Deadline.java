package Tasks;
public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return this.by;
    }
    @Override
    public String getType() {
        return "deadline";
    }
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[D][X] " : "[D][ ] ") + this.description +
                " (by: " + this.by + ")";
        return fullSentence;
    }
}
