public class Deadline extends Task {
    private final String deadlineBy;

    public Deadline(String taskName, String deadlineBy) {
        super(taskName);
        this.deadlineBy = deadlineBy.trim();
    }

    @Override
    public String getTaskStatus() {
        return "[D]" +super.getTaskStatus()
                + " (by: " + deadlineBy + ")";
    }
}
