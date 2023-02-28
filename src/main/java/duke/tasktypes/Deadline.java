package duke.tasktypes;

import duke.common.Common;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task{
    private String date;

    /**
     * Constructs a new Deadline object with the given content and deadline date.
     * @param content the content of the task.
     * @param date the deadline date of the task.
     */
    public Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    @Override
    public String printTask() {
        return "[D]" + this.getMarkingStatus() + " " + this.content + " (by: " + this.date + ")";
    }

    @Override
    public String putInputToDataFile() {
        return "D | " + this.convertMarkingStatusToNumber() + Common.VERTICAL_BAR  + this.content
                + Common.VERTICAL_BAR  + this.date + "\n";
    }
}
