package duke.tasktypes;

import duke.common.Common;

/**
 * Represents a task of type "Event".
 */
public class Event extends Task {

    private String beginTime;
    private String endTime;

    /**
     * Creates a new Event object with the given content, beginning time and end time.
     * @param content The content of the Event task.
     * @param beginTime The beginning time of the Event task.
     * @param endTime The end time of the Event task.
     */
    public Event(String content, String beginTime, String endTime) {
        super(content);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String printTask() {
        return "[E]" + this.getMarkingStatus() + Common.WHITE_SPACE + this.content + " (from: " + this.beginTime
                + " to " + this.endTime + ")";
    }

    @Override
    public String putInputToDataFile() {
        return "E | " + this.convertMarkingStatusToNumber() + Common.VERTICAL_BAR
                + this.content + Common.VERTICAL_BAR  + this.beginTime + Common.VERTICAL_BAR  + this.endTime + "\n";
    }

}
