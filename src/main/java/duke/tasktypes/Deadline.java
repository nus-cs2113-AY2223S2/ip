package duke.tasktypes;

import duke.common.Common;

public class Deadline extends Task{
    private String date;

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
