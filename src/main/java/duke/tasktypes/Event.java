package duke.tasktypes;

public class Event extends Task {
    private String beginDate;
    private String endDate;

    public Event(String content, String beginDate, String endDate) {
        super(content);
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    @Override
    public String printTask() {
        return "[E]" + this.getMarkingStatus() + " " + this.content + " (from: " + this.beginDate
                + " to " + this.endDate + ")";
    }

    @Override
    public String putInputToDataFile() {
        return "E | " + this.convertMarkingStatusToNumber() + " | " + this.content + " | " + this.beginDate
                + " | " + this.endDate + "\n";
    }
}
