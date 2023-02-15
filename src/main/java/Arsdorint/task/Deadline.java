package Arsdorint.task;

public class Deadline extends Task {
    public String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[D]";
    }
    public void printTask() {
        System.out.println(this.taskType + this.getStatus()
        + " " + this.description + "(" + this.date + ")");
    }

}
