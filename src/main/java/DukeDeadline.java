import java.util.Optional;

public class DukeDeadline extends DukeTask {
    protected String Deadline;
    public DukeDeadline(String TaskName, String Deadline) {
        super(TaskName);
        this.Deadline = Deadline;
    }
    @Override
    public void printTask(int i) { System.out.println((i+1) + ".[D] [" + this.getStatusIcon() + "] " + this.TaskName + " (by: " + this.Deadline + ")"); }
    @Override
    public void printTask() { System.out.println("[D] [" + this.getStatusIcon() + "] " + this.TaskName + " (by: " + this.Deadline + ")"); }
}
