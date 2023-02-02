public class DukeEvent extends DukeTask {
    protected String eventFrom;
    protected String eventTo;
    public DukeEvent(String taskName, String eventFrom, String eventTo) {
        super(taskName);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: " + this.eventFrom + " to: " + this.eventTo + ")");
    }
    @Override
    public void printTask() {
        System.out.println("[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: " + this.eventFrom + " to: " + this.eventTo + ")");
    }
}
