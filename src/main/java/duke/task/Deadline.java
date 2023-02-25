package duke.task;

public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String description, String type, String by) {
        super(description, type);
        this.dueDate = by;
    }

    @Override
    public String toString() {
        return super.toString() + " by: " + this.dueDate;
    }

}