package duke.tasktypes;

public class ToDo extends Task{
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String printTask() {
        return "[T]" + this.getMarkingStatus() + " " + this.content;
    }

    @Override
    public String putInputToDataFile() {
        return "T | " + this.convertMarkingStatusToNumber() + " " + this.content + "\n";
    }
}
