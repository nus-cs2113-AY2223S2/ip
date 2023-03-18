package task;

public class Deadline extends Task {

    protected String dateDue;
    public Deadline(String name, String dateDue) {
        super(name);
        this.dateDue = dateDue;
    }

    public String toString() {
        return "(by " + dateDue + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
