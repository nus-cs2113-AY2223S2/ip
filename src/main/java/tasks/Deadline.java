package tasks;


public class Deadline extends Task {

    protected String by;


    public Deadline(String ins, String description, String by) {
        super(ins, description);
        this.type = 'D';
        this.by = by;
    }

    @Override
    public String toString() {
        return ".[D]" + super.toString() + " (by: " + by + ")";
    }
}
