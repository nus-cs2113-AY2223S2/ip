package alltasks;

public class Deadline extends Task {

    public Deadline(String descriptor) {
        super(descriptor);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "]" + " " + description;
    }
}
