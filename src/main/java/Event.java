public class Event extends Task {
    String byDate;
    String fromDate;

    public Event(String task, int number, boolean isDone, String byDate, String fromDate) {
        super(task, number, isDone);
        this.type = "E";
        this.byDate = byDate;
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + fromDate + " to: " + byDate + ")";
    }
}
