package dataypes;

public class Deadlines extends Task implements TaskFileHandler {
    protected String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
    public Deadlines(){} //more when you get to decode and less in enCode
    public String getDeadline() {
        return this.deadline;
    }
//    public void printAddedDeadline() {
//        System.out.println("\tGot it. I've added this task:\n");
//        System.out.println("\t\t" + this.getStatusAndDescription());
//    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    @Override
    public String getStatusAndDescription() {
        return "[D]" + super.getStatusAndDescription() + "(by: " + this.getDeadline() + ")";
    }
    public String enCode() {
        return "D # " + super.enCode() + " # " + this.deadline;
    }
}
