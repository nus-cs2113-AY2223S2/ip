public class Deadline extends Task {
    protected String cutoffDate;

    public Deadline(String description, String cutoffDate) {
        super(description);
        this.cutoffDate = cutoffDate;
    }

    @Override
    public String toString() {
        return "[DEADLINE]\n" + super.toString() + " (By: " + cutoffDate + ")";
    }

}
