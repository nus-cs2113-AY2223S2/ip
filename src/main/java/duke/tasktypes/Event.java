package duke.tasktypes;

import duke.common.Common;

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
        return "[E]" + this.getMarkingStatus() + Common.WHITE_SPACE + this.content + " (from: " + this.beginDate
                + " to " + this.endDate + ")";
    }

    @Override
    public String putInputToDataFile() {
        return "E | " + this.convertMarkingStatusToNumber() + Common.VERTICAL_BAR
                + this.content + Common.VERTICAL_BAR  + this.beginDate + Common.VERTICAL_BAR  + this.endDate + "\n";
    }
}
