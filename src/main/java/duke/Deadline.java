package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    public String toString() {
        if (this.getIsDone() == true) {
            return " [D][X] " + this.getName() + " (by: " + this.by + ")";
        }
        return " [D][ ] " + this.getName() + " (by: " + this.by + ")";
    }

    public void print() {
        if (this.isIsDone() == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }
}
