public class Deadline extends Tasks {
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }
    public String getDeadline() {
        String[] d = deadline.split("\\s+");
        return "(" + d[0] + ": " + d[1] + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}
