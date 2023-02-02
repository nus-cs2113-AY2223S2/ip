public class Event extends Task {
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
