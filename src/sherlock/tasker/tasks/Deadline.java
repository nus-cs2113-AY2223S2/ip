package tasks;

public class Deadline extends Task {

    private String by;
    public Deadline(String name, Boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


    public String toString() {
        return "[D]" + super.toString() + " (" + "by: " + by + ")";
    }

    @Override
    public String getFileFormatString() {
        return String.format("%s | %d | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.name,
                this.by
        );
    }
}
