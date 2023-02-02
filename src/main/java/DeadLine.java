public class DeadLine extends Task {
    private final String deadLineBy;

    public DeadLine(String taskName, String deadLineBy) {
        super(taskName);
        this.deadLineBy = deadLineBy;
    }

    @Override
    public String getTaskStatus() {
        return "[D]" +super.getTaskStatus()
                + "(by: " + deadLineBy + ")";
    }
}
