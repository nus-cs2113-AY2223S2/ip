package Duke.DukeTask;

public class DukeDeadline extends DukeTask {
    protected String deadline;
    public DukeDeadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " + this.deadline + ")");
    }
    @Override
    public void printTask() {
        System.out.println("[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " + this.deadline + ")");
    }
}
