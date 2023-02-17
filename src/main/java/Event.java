public class Event extends Task {
    protected String startDate;
    protected String endDate;
    Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public void printTaskType() {
        System.out.print("E");
    }
    @Override
    public void printTask() {
        System.out.print("[");
        printTaskType();
        System.out.print("][");
        if (super.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName() + "(from:" + startDate + " to:" + endDate + ")");
    }
}
