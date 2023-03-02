package duke.task;

public class Event extends Task {
    String start; String end;
    String taskDescription;
    public Event (String taskDescription, String start, String end) {
        super(taskDescription + " (from: " + start + " to: " + end + ")");
        this.start = start;
        this.end = end;
        this.taskDescription = taskDescription;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toSaveString(String... taskParameters) {
        return super.toSaveString("E", isDone ? "1" : "0", taskDescription, start, end);
    }
}
