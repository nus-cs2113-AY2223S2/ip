package duke.tasktypes;

import duke.common.Common;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task{

    private String time;

    /**
     * Constructs a new Deadline object with the given content and deadline time.
     * @param content the content of the task.
     * @param time the deadline time of the task.
     */
    public Deadline(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String printTask() {
        return "[D]" + this.getMarkingStatus() + " " + this.content + " (by: " + this.time + ")";
    }

    @Override
    public String putInputToDataFile() {
        return "D | " + this.convertMarkingStatusToNumber() + Common.VERTICAL_BAR  + this.content
                + Common.VERTICAL_BAR  + this.time + "\n";
    }
}
