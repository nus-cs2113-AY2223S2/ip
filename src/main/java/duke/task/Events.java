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

    public String getStart(){
        return this.start;
    }

    public String getEnd(){
        return this.end;
    }

    @Override
    public String toString(){
        return checkBoxOutput() + this.getTaskName() + " (from: " + this.getStart() + " to: "+ this.getEnd() + ")";
    }
}
