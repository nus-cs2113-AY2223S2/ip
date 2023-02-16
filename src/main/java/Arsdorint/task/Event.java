package Arsdorint.task;

public class Event extends Task {
    public static final String typeEvent = "E";
    public String date;
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[E]";
        this.taskName = typeEvent;
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
