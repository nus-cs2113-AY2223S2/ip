public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String fullDescription() {
        String full;
        full = (isDone ? "[D][X] " : "[D][ ] ") + this.description +
                " (by: " + this.by + ")";
        return full;
    }
}
