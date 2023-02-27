package duke.tasktypes;

import duke.common.Common;

public class ToDo extends Task{

    public ToDo(String content) {
        super(content);
    }

    @Override
    public String printTask() {
        return "[T]" + this.getMarkingStatus() + Common.WHITE_SPACE + this.content;
    }

    @Override
    public String putInputToDataFile() {
        return "T | " + this.convertMarkingStatusToNumber() + Common.VERTICAL_BAR + this.content + "\n";
    }
}
