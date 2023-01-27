public class Deadline extends Task {

    protected String by;

    public Deadline(String description, int num, String by) {
        super(description,num);
        this.by = by;
    }

    @Override
    public String toString() {
        return num + ".[D]" + super.toString() + " (by: " + by + ")";
    }
}
