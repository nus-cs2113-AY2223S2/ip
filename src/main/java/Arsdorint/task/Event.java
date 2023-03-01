package Arsdorint.task;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";
    public String date;
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[E]";
        this.taskName = TYPE_EVENT;
    }

    public void printTask() {
        System.out.println(this.taskType + this.getStatus()
                + " " + this.description + "(" + this.date + ")");
    }
    @Override
    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR
                + this.description + VERTICAL_BAR + this.date + "\n");
    }
}
