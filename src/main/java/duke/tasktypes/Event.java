package duke.tasktypes;

import duke.common.Common;

/**
 * Represents a task of type "Event".
 */
public class Event extends Task {
    private String beginDate;
    private String endDate;

    /**
     * Creates a new Event object with the given content, begin date and end date.
     * @param content The content of the Event task.
     * @param beginDate The beginning date of the Event task.
     * @param endDate The end date of the Event task.
     */
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
