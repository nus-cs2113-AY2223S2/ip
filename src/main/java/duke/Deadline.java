package duke;
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getType(){
        return "D";
    }

    public String getDeadline(){
        return " (by: " + by + ")";
    }
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
