import java.util.Optional;
public class DukeEvent extends DukeTask {
    protected String EventFrom;
    protected String EventTo;
    public DukeEvent(String TaskName, String EventFrom, String EventTo) {
        super(TaskName);
        this.EventFrom = EventFrom;
        this.EventTo = EventTo;
    }
    @Override
    public void printTask(int i) { System.out.println((i+1) + ".[E] [" + this.getStatusIcon() + "] " + this.TaskName + " (from: " + this.EventFrom + " to: " + this.EventTo + ")"); }
    @Override
    public void printTask() { System.out.println("[E] [" + this.getStatusIcon() + "] " + this.TaskName + " (from: " + this.EventFrom + " to: " + this.EventTo + ")"); }
}
