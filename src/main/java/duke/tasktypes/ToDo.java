package duke.tasktypes;

import duke.common.Common;

/**
 * Represents a task of type "To Do".
 */
public class ToDo extends Task{

    /**
     Creates a new ToDo object with the given content.
     @param content The content of the ToDo task.
     */
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
