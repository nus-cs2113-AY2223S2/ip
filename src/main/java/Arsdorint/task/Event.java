package Arsdorint.task;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";
    public static final String TYPE_EVENT_BOX = "[E}";
    public String date;
    public Event(String description, String Date) {
        super(description);
        this.date = date;
        this.taskType = "[E]";
    }

    public void printTask() {
        System.out.println(this.taskType + this.getStatus()
                + " " + this.description + "(" + this.date + ")");
    }
}
