package duke.task;

public class Events extends ToDo {

    protected String start;
    protected String end;

    public Events(String taskName, String start, String end) {
        super(taskName);
        super.type = "[E]";
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){
        return checkBoxOutput() + this.taskName + " (from: " + this.start + " to: "+ this.end + ")";
    }
}
