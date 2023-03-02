public class Deadline extends Task {

    String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public String toString() {
        return ("[D]" + super.toString() + " (by: " + due + ")");
    }
}