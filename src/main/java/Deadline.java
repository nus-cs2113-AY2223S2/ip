public class Deadline extends Task{
    private final String deadline;
    public Deadline(String taskName, int index, String deadline) {
        super(taskName, index);
        this.deadline = deadline;
    }
    public String getDeadline() {
        return this.deadline;
    }
}
