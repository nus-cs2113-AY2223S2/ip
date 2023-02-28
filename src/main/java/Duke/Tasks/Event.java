package Duke.Tasks;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public void printTask() {
        System.out.print("[");
        System.out.print(getTaskType());
        System.out.print("][");
        if (super.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName() + "(from:" + startDate + " to:" + endDate + ")");
    }

    @Override
    public String saveInfo() {
        return getTaskType() + "t/" +
                (isDone ? "X" : "Y") + "m/" +
                taskName + "n/" +
                startDate + "s/" +
                endDate + "\n";
    }
}
