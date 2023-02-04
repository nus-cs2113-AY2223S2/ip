package duke.tasks;

public class Deadline extends Task {
    private String byWhen;

    public Deadline(String name, String by) {
        super(name, "D");
        this.byWhen = by;
    }

    public String getByWhen() {
        return byWhen;
    }

    public String toString() {
        return super.toString() + " (by: " + byWhen + ')';
    }
}
