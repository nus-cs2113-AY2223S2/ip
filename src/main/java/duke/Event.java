package duke;

public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void getTaskStatus() {
        System.out.printf("[E][%s] %s (from: %s to: %s)\n", this.getDone(), this.getTaskName(),
                this.startDate, this.endDate);
    }

    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
}
