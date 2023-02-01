public class Deadline extends Task{
    private String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
        setDescription(getDescription() + '(' + by + ')');
    }

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }
}
